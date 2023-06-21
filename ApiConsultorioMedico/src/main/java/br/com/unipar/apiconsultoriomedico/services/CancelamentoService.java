package br.com.unipar.apiconsultoriomedico.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unipar.apiconsultoriomedico.model.Cancelamento;
import br.com.unipar.apiconsultoriomedico.repositories.CancelamentoRepository;


@Service
public class CancelamentoService {
	
	@Autowired
	private CancelamentoRepository cancelamentoRepository;
	
	public Cancelamento insert(Cancelamento cancelamento) throws Exception {
		
		cancelamentoRepository.saveAndFlush(cancelamento);
		
		return cancelamento;
	}
	
	public Cancelamento findById(Long id) throws Exception{
		
		Optional<Cancelamento> retorno = cancelamentoRepository.findById(id);
		
		if(retorno.isPresent())
			return retorno.get();
		
		else {
			throw new Exception("Cancelamento com Id"+id+"Não Identificado");
		}
	}
}
