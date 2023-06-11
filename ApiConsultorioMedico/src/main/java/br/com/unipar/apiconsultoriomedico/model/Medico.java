package br.com.unipar.apiconsultoriomedico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

import br.com.unipar.apiconsultoriomedico.EnumEspecialidade;

@Entity
@Table(name = "medico")
@DynamicUpdate
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String nome;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Column(updatable = false)
	private String email;
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String telefone;
	
	@NotNull
	@NotEmpty
	@NotBlank
	@Column(updatable = false)
	private int crm;
	
	@OneToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	
	@NotNull
	@Column(updatable = false)
	@Enumerated(EnumType.STRING)
	private EnumEspecialidade enumespecialidade;
	
	
	private Boolean status;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getCrm() {
		return crm;
	}

	public void setCrm(int crm) {
		this.crm = crm;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public EnumEspecialidade getEnumespecialidade() {
		return enumespecialidade;
	}

	public void setEnumespecialidade(EnumEspecialidade enumespecialidade) {
		this.enumespecialidade = enumespecialidade;
	}
	
	

}
