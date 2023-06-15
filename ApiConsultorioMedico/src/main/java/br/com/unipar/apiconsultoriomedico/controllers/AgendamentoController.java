package br.com.unipar.apiconsultoriomedico.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unipar.apiconsultoriomedico.model.Agendamento;
import br.com.unipar.apiconsultoriomedico.services.AgendamentoService;

@RestController
@RequestMapping(path = "/agendamento")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoService agendamentoService;
	
	@PostMapping
	public Agendamento insert(@RequestBody @Valid Agendamento agendamento) throws Exception{
		
		return agendamentoService.insert(agendamento);
	}

}
