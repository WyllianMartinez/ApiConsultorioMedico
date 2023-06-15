package br.com.unipar.apiconsultoriomedico.resquest;

import br.com.unipar.apiconsultoriomedico.model.Agendamento;

public class CancelamentoRequest {
    private Agendamento agendamento;
    private String motivo;
	
    
    public Agendamento getAgendamento() {
		return agendamento;
	}
	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

    
    
}