package br.com.unipar.apiconsultoriomedico.model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.unipar.apiconsultoriomedico.enums.MotivoCancelamento;

@Entity
@Table
public class Cancelamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private MotivoCancelamento motivoCancelamento;
	
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "id_agendamento")
	private Agendamento agendamento;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public MotivoCancelamento getMotivoCancelamento() {
		return motivoCancelamento;
	}


	public void setMotivoCancelamento(MotivoCancelamento motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}


	public Agendamento getAgendamento() {
		return agendamento;
	}


	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	
	
	
	
	

}
