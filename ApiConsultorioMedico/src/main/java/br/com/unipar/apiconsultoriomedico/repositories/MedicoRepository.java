package br.com.unipar.apiconsultoriomedico.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unipar.apiconsultoriomedico.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
	
	public List<Medico> findByNomeOrderByNomeAsc(String nome);
	
	

}
