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
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	
	@PostMapping
	@ApiOperation(value = "Inserir um endereco")
	public Endereco insert(@RequestBody @Valid Endereco endereco) throws Exception{
		
		return enderecoService.insert(endereco);
		
	}
	
	@PutMapping
	@ApiOperation(value = "Editar um endereco")
	public Endereco edit(@RequestBody Endereco endereco) throws Exception {
		
		return enderecoService.edit(endereco);
	}
	
	@GetMapping
	@ApiOperation(value = "Listagem de enderecos")
	public List<Endereco> findAll() throws Exception{
		
		return enderecoService.findAll();
		
	}
	
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Buscar um endereco pelo ID")
	public Endereco findById(@PathVariable Long id) throws Exception{
		
		return enderecoService.findById(id);
		
	}
}
