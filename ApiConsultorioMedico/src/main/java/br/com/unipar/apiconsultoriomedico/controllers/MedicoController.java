package br.com.unipar.apiconsultoriomedico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.unipar.apiconsultoriomedico.model.Medico;
import br.com.unipar.apiconsultoriomedico.repositories.MedicoRepository;
import br.com.unipar.apiconsultoriomedico.services.MedicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/medico" )
@Api(tags = "API Medico", description = "Medico")
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;
	
	@PostMapping
	@ApiOperation(value = "Inserir um medico")
	public Medico insert(@RequestBody Medico medico)throws Exception  {
	
		return medicoService.insert(medico);
	 
	}
	
	
	@PutMapping
	@ApiOperation(value = "Editar uma medico")
	public Medico edit(@RequestBody Medico medico) throws Exception{
		
		return medicoService.edit(medico);
			
	}
	
	@GetMapping
	@ApiOperation(value = "Obter uma lista de medico")
	public List<Medico> findAll() throws Exception{
	
			return medicoService.findAll();
		
	}
	
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Obter um medico pelo seu ID")
	public Medico findById(@PathVariable Long id) throws Exception{
		
		return medicoService.findById(id);
		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar um medico")
	public Medico delete(@PathVariable Long id) {
	    return medicoService.delete(id);
	}
	
	@GetMapping(path = "/ordenacao")
    @ApiOperation(value = "Lista de medicos ordenada pelo nome")
    public List<MedicoRepository.MedicoProjection> findByFilters(){

        return medicoService.findAllOrder();
    }

}