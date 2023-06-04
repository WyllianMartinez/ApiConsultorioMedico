package br.com.unipar.apiconsultoriomedico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unipar.apiconsultoriomedico.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
	
	
}
