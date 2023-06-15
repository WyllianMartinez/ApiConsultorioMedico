package br.com.unipar.apiconsultoriomedico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import br.com.unipar.apiconsultoriomedico.model.Agendamento;
import br.com.unipar.apiconsultoriomedico.repositories.AgendamentoRepository;

@Service
public class AgendamentoService {
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	public Agendamento insert(Agendamento agendamento) throws Exception{
		
		agendamentoRepository.saveAndFlush(agendamento);
		
		return agendamento;
	}
	
	public Agendamento findById(Long id) throws Exception{
		
		Optional<Agendamento> retorno = agendamentoRepository.findById(id);
		
		if(retorno.isPresent())
			return retorno.get();
		else
			throw new Exception("Agendamento com Id"+id+"Não Identificado");
	}

}
