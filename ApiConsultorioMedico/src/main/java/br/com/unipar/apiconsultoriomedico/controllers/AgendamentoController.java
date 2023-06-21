package br.com.unipar.apiconsultoriomedico.controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.unipar.apiconsultoriomedico.enums.MotivoCancelamento;
import br.com.unipar.apiconsultoriomedico.exceptions.ConsultaAgendamentoException;
import br.com.unipar.apiconsultoriomedico.exceptions.ConsultaCancelamentoException;
import br.com.unipar.apiconsultoriomedico.model.Agendamento;
import br.com.unipar.apiconsultoriomedico.model.Cancelamento;
import br.com.unipar.apiconsultoriomedico.model.Endereco;
import br.com.unipar.apiconsultoriomedico.model.Medico;
import br.com.unipar.apiconsultoriomedico.model.Paciente;
import br.com.unipar.apiconsultoriomedico.resquest.AgendamentoRequest;
import br.com.unipar.apiconsultoriomedico.resquest.CancelamentoRequest;
import br.com.unipar.apiconsultoriomedico.services.AgendamentoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/consultas")
public class AgendamentoController {
	
	private AgendamentoService agendamentoService;

    private List<Agendamento> agendamentos = new ArrayList<>();
    private List<Cancelamento> cancelamento = new ArrayList<>();

    @PostMapping("/agendar")
    @ApiOperation(value = "Agendar uma consulta")
    public void insert(@RequestBody AgendamentoRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(request.getData(), formatter);
        LocalTime horario = request.getHorario();

        if (!validarHorarioFuncionamento(data, horario)) {
            throw new ConsultaAgendamentoException("Horário de agendamento inválido");
        }

        if (!isPacienteAtivo(request.getPaciente())) {
            throw new ConsultaAgendamentoException("Paciente inativo");
        }

        if (!isMedicoAtivo(request.getMedico())) {
            throw new ConsultaAgendamentoException("Médico inativo");
        }

        if (isConsultaDuplicada(request.getPaciente(), data)) {
            throw new ConsultaAgendamentoException("Já existe uma consulta agendada para o paciente neste dia");
        }

        if (isMedicoOcupado(request.getMedico(), LocalDateTime.of(data, horario))) {
            throw new ConsultaAgendamentoException("Médico já possui uma consulta agendada neste horário");
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setMedico(request.getMedico());
        agendamento.setPaciente(request.getPaciente());
        agendamento.setDatahora(LocalDateTime.of(data, horario));
        agendamentos.add(agendamento);
    }

    @PostMapping("/cancelar")
    @ApiOperation(value = "Cancelar uma consulta")
    public void cancelarConsulta(@RequestBody CancelamentoRequest request) {
        Agendamento agendamento = request.getAgendamento();
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        LocalDateTime dataHoraAgendamento = agendamento.getDatahora();
        
        
        if (dataHoraAgendamento.isBefore(dataHoraAtual.plus(24, ChronoUnit.HOURS))) {
        	agendamento.setCancelada(true);
            throw new ConsultaCancelamentoException("Não é possível cancelar a consulta com a antecedência mínima de 24 horas");
            
        }
        
        agendamento.setCancelada(false);
    }

    // Outros métodos e validações...

    private boolean validarHorarioFuncionamento(LocalDate data, LocalTime horario) {
        DayOfWeek diaSemana = data.getDayOfWeek();
        LocalTime horarioInicio = LocalTime.of(7, 0);
        LocalTime horarioFim = LocalTime.of(19, 0);

        return diaSemana != DayOfWeek.SUNDAY && diaSemana != DayOfWeek.MONDAY &&
                horario.isAfter(horarioInicio) && horario.isBefore(horarioFim);
    }

    private boolean validarAntecedenciaMinima(LocalDate data, LocalTime horario) {
        LocalDateTime dataHoraConsulta = LocalDateTime.of(data, horario);
        LocalDateTime antecedenciaMinima = LocalDateTime.now().plusMinutes(30);

        return dataHoraConsulta.isAfter(antecedenciaMinima);
    }

    private boolean isPacienteAtivo(Paciente paciente) {
        
        return true;
    }

    private boolean isMedicoAtivo(Medico medico) {
        
        return true;
    }

    private boolean isConsultaDuplicada(Paciente paciente, LocalDate data) {

        for (Agendamento agendamento : agendamentos) {
            if (!agendamento.isCancelada() && agendamento.getPaciente().equals(paciente) && agendamento.getDatahora().toLocalDate().equals(data)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMedicoOcupado(Medico medico, LocalDateTime dataHora) {

        for (Agendamento agendamento : agendamentos) {
            if (!agendamento.isCancelada() && agendamento.getMedico().equals(medico) && agendamento.getDatahora().equals(dataHora)) {
                return true;
            }
        }
        return false;
    }

    
    @GetMapping
    @ApiOperation(value = "Obter uma lista de consultas")
    public List<Agendamento> findAll() throws Exception {
        return agendamentoService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Obter uma consulta pelo seu ID")
    public Agendamento findById(@PathVariable Long id) throws Exception {
        return agendamentoService.findById(id);
    }

}
