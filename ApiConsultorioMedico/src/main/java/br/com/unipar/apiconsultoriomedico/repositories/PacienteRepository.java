package br.com.unipar.apiconsultoriomedico.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.unipar.apiconsultoriomedico.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	
	@Query
	public List<Paciente> findByNomeContainingAllIgnoCase(String nome);
	

}
