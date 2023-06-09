package br.com.unipar.apiconsultoriomedico.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.unipar.apiconsultoriomedico.model.Paciente;
import br.com.unipar.apiconsultoriomedico.repositories.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public Paciente insert(Paciente paciente) throws Exception{
		
		pacienteRepository.saveAndFlush(paciente);
		
		return paciente;
		
	}
	
	public Paciente edit(Paciente paciente) throws Exception {
		
		pacienteRepository.saveAndFlush(paciente);
		
		return paciente;
		
		
	}
		
	public List<Paciente> findAll(){
		
		return pacienteRepository.findAll();
		
	}
	
	public List<Paciente> findByFilters(String nome){
		
		return pacienteRepository.findByNomeContainingIgnoreCase(nome);
		
	}
	
	public Paciente findById(Long id) throws Exception {
		
		Optional<Paciente> retorno = pacienteRepository.findById(id);
		
		if(retorno.isPresent())
			return retorno.get();
		else 
			throw new Exception("Paciente com ID"+id+"Não identificado");
		
	}
}

