package br.com.unipar.apiconsultoriomedico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unipar.apiconsultoriomedico.model.Medico;
import br.com.unipar.apiconsultoriomedico.repositories.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;

	public Medico insert(Medico medico) throws Exception {

		medicoRepository.saveAndFlush(medico);

		return medico;
	}

	public Medico edit(Medico medico) throws Exception {

		medicoRepository.saveAndFlush(medico);

		return medico;
	}

	public List<Medico> findAll() {

		return medicoRepository.findAll();
	}

	public List<Medico> findByFilters(String nome) {

		return medicoRepository.findByNomeContainingAllIgnoCase(nome);
	}

	public Medico findById(Long id) throws Exception{
		
		Optional<Medico> retorno = medicoRepository.findById(id);
		
		if(retorno.isPresent())
			return retorno.get();
		else {
			throw new Exception("Médico com Id"+id+" Não Identificado");
		}
	}
}

