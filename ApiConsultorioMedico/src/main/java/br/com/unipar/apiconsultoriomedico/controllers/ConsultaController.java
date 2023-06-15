package br.com.unipar.apiconsultoriomedico.controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.unipar.apiconsultoriomedico.exceptions.ConsultaAgendamentoException;
import br.com.unipar.apiconsultoriomedico.exceptions.ConsultaCancelamentoException;
import br.com.unipar.apiconsultoriomedico.model.Agendamento;
import br.com.unipar.apiconsultoriomedico.model.Medico;
import br.com.unipar.apiconsultoriomedico.model.Paciente;
import br.com.unipar.apiconsultoriomedico.resquest.AgendamentoRequest;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private List<Agendamento> agendamentos = new ArrayList<>();

    @PostMapping("/agendar")
    public void agendarConsulta(@RequestBody AgendamentoRequest request) {
        LocalDate data = request.getData();
        LocalTime horario = request.getHorario();

        // Verifica se a data está dentro do horário de funcionamento da clínica
        if (!validarHorarioFuncionamento(data, horario)) {
            throw new ConsultaAgendamentoException("Horário de agendamento inválido");
        }

        // Verifica se a consulta possui antecedência mínima de 30 minutos
        if (!validarAntecedenciaMinima(data, horario)) {
            throw new ConsultaAgendamentoException("Antecedência mínima não atendida");
        }

        // Verifica se o paciente está ativo no sistema
        if (!isPacienteAtivo(request.getPaciente())) {
            throw new ConsultaAgendamentoException("Paciente inativo");
        }

        // Verifica se o médico está ativo no sistema
        if (!isMedicoAtivo(request.getMedico())) {
            throw new ConsultaAgendamentoException("Médico inativo");
        }

        // Verifica se o paciente já possui uma consulta agendada no mesmo dia
        if (isConsultaDuplicada(request.getPaciente(), data)) {
            throw new ConsultaAgendamentoException("Já existe uma consulta agendada para o paciente neste dia");
        }

        // Verifica se o médico já possui uma consulta agendada na mesma data/hora
        if (isMedicoOcupado(request.getMedico(), LocalDateTime.of(data, horario))) {
            throw new ConsultaAgendamentoException("Médico já possui uma consulta agendada neste horário");
        }

        // Agendamento da consulta
        Agendamento agendamento = new Agendamento(request.getPaciente(), request.getMedico(), LocalDateTime.of(data, horario));
        agendamentos.add(agendamento);
    }

    @PostMapping("/cancelar")
    public void cancelarConsulta(@RequestBody CancelamentoRequest request) {
        Agendamento agendamento = request.getAgendamento();

        // Verifica se a consulta pode ser cancelada com antecedência mínima de 24 horas
        if (!validarAntecedenciaCancelamento(agendamento.getDatahora())) {
            throw new ConsultaCancelamentoException("Não é possível cancelar a consulta com a antecedência mínima");
        }

        // Realiza o cancelamento da consulta
        agendamento.setCancelada(true);
        agendamento.setMotivoCancelamento(request.getMotivo());
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
        // Lógica para verificar se o paciente está ativo no sistema
        return true;
    }

    private boolean isMedicoAtivo(Medico medico) {
        // Lógica para verificar se o médico está ativo no sistema
        return true;
    }

    private boolean isConsultaDuplicada(Paciente paciente, LocalDate data) {
        // Verifica se o paciente já possui uma consulta agendada no mesmo dia
        for (Agendamento agendamento : agendamentos) {
            if (!agendamento.isCancelada() && agendamento.getPaciente().equals(paciente) && agendamento.getDatahora().toLocalDate().equals(data)) {
                return true;
            }
        }
        return false;
    }

    private boolean isMedicoOcupado(Medico medico, LocalDateTime dataHora) {
        // Verifica se o médico já possui uma consulta agendada na mesma data/hora
        for (Agendamento agendamento : agendamentos) {
            if (!agendamento.isCancelada() && agendamento.getMedico().equals(medico) && agendamento.getDatahora().equals(dataHora)) {
                return true;
            }
        }
        return false;
    }

    private boolean validarAntecedenciaCancelamento(LocalDateTime dataHoraConsulta) {
        LocalDateTime antecedenciaMinima = LocalDateTime.now().plusHours(24);
        return dataHoraConsulta.isAfter(antecedenciaMinima);
    }

    @ExceptionHandler(ConsultaAgendamentoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConsultaAgendamentoException(ConsultaAgendamentoException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ConsultaCancelamentoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConsultaCancelamentoException(ConsultaCancelamentoException ex) {
        return ex.getMessage();
    }
}
