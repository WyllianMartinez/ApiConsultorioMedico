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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.unipar.apiconsultoriomedico.model.Paciente;
import br.com.unipar.apiconsultoriomedico.repositories.PacienteRepository;
import br.com.unipar.apiconsultoriomedico.services.PacienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/paciente")
@Api(tags = "Api Paciente", description = "Paciente")
public class PacienteController {
	
	@Autowired
	private PacienteService pacienteService;
	
	@PostMapping
	@ApiOperation(value = "Inserir um Paciente")
	public Paciente insert(@RequestBody Paciente paciente) throws Exception {
		
		return pacienteService.insert(paciente);
	}
	
	@PutMapping
	@ApiOperation(value = "Editar um paciente")
	public Paciente edit(@RequestBody Paciente paciente) throws Exception {

		return pacienteService.edit(paciente);
	}
	
	@GetMapping
	@ApiOperation(value = "Obter uma lista de paciente")
	public List<Paciente> findAll() throws Exception {
	
	return pacienteService.findAll();
	
	}
	
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Obter um paciente pelo seu ID")
	public Paciente findById(@PathVariable Long id) throws Exception {
		
		return pacienteService.findById(id);
		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar um paciente")
	public Paciente delete(@PathVariable Long id) {
	    return pacienteService.delete(id);
	}
	
	@GetMapping(path = "/busca")
    @ApiOperation(value = "Buscar paciente pelo seu nome")
    public List<Paciente> findByFilters(@RequestParam("nome") String nome){

        return pacienteService.findByFilters(nome);
    }

    @GetMapping(path = "/ordenacao")
    @ApiOperation(value = "Lista de pacientes ordenadas pelo nome")
    public List<PacienteRepository.PacienteProjection> findByFilters(){

        return pacienteService.findAllOrder();
    }


}
