package br.com.unipar.apiconsultoriomedico.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import org.springframework.data.annotation.Id;

import br.com.unipar.apiconsultoriomedico.EnumEspecialidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "medico")
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
