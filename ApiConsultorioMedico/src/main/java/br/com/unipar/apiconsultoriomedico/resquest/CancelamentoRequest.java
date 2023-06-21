package br.com.unipar.apiconsultoriomedico.resquest;

import java.sql.Date;
import java.time.LocalDateTime;

import br.com.unipar.apiconsultoriomedico.enums.MotivoCancelamento;
import br.com.unipar.apiconsultoriomedico.model.Agendamento;

public class CancelamentoRequest {
    private Agendamento agendamento;
    private MotivoCancelamento motivoCancelamento;
    private LocalDateTime dataHora;
	
    
    public Agendamento getAgendamento() {
		return agendamento;
	}
	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}	
	public MotivoCancelamento getMotivoCancelamento() {
		return motivoCancelamento;
	}
	public void setMotivoCancelamento(MotivoCancelamento motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public static LocalDateTime getDatahora() {
		
		return LocalDateTime.now();
	}
	
}