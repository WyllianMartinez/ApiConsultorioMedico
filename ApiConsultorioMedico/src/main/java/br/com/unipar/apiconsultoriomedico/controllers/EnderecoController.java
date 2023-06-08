package br.com.unipar.apiconsultoriomedico.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unipar.apiconsultoriomedico.model.Endereco;
import br.com.unipar.apiconsultoriomedico.services.EnderecoService;

@RestController
@RequestMapping(path = "/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	
	@PostMapping
	public Endereco insert(@RequestBody @Valid Endereco endereco) throws Exception{
		
		return enderecoService.insert(endereco);
		
	}
	
	@PutMapping
	public Endereco edit(@RequestBody Endereco endereco) throws Exception {
		
		return enderecoService.edit(endereco);
	}
	
	@GetMapping
	public List<Endereco> findAll() throws Exception{
		
		return enderecoService.findAll();
		
	}
	
	@GetMapping(path = "/{id}")
	public Endereco findById(@PathVariable Long id) throws Exception{
		
		return enderecoService.findById(id);
		
	}
}
