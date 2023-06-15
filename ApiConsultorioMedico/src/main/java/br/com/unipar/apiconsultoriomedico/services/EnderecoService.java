package br.com.unipar.apiconsultoriomedico.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.unipar.apiconsultoriomedico.model.Endereco;
import br.com.unipar.apiconsultoriomedico.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco insert(Endereco endereco) throws Exception{
		
		enderecoRepository.saveAndFlush(endereco);
		
		return endereco;
		
	}
	
	public Endereco edit(Endereco endereco) throws Exception{
		
		enderecoRepository.saveAndFlush(endereco);
		
		return endereco;
		
	}
	
	public List<Endereco> findAll(){
		
		return enderecoRepository.findAll();
		
	}
	
	public Endereco findById(Long id) throws Exception{
		
		Optional<Endereco> retorno = enderecoRepository.findById(id);
		
		if (retorno.isPresent())
			return retorno.get();
		else
			throw new Exception("Endereco com Id "+id+" Não Identificado");
		
	}
}
