package br.com.unipar.apiconsultoriomedico.resquest;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.unipar.apiconsultoriomedico.model.Medico;
import br.com.unipar.apiconsultoriomedico.model.Paciente;

public class AgendamentoRequest {
    private Paciente paciente;
    private Medico medico;
    private LocalDate data;
    private LocalTime horario;
	
    
    
    public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

    

}