package br.com.unipar.apiconsultoriomedico.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Agendamento")
public class Agendamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@ManyToOne
	@JoinColumn(name = "Paciente_id")
	private Paciente paciente;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@ManyToOne
	@JoinColumn(name = "Medico_id")
	private Medico medico;
	
	@NotNull
	@NotEmpty
	@NotBlank
	private Date datahora;
	
	@Column(name = "cancelada")
	private boolean cancelada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getDatahora() {
		return datahora;
	}

	public void setDatahora(Date datahora) {
		this.datahora = datahora;
	}
	
	public boolean isCancelada() {
		return cancelada;
	}

	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}

	// Outros métodos, se necessário
}
