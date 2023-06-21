package br.com.unipar.apiconsultoriomedico.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.unipar.apiconsultoriomedico.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	
	List<Paciente> findByNomeContainingIgnoreCase(String nome);

	@Query("SELECT p.nome AS nome, p.cpf AS cpf, p.email AS email FROM Paciente p ORDER BY p.nome ASC")
    List<PacienteProjection> findAllByOrderByNomeAsc();

        interface PacienteProjection {
            String getNome();
            String getCpf();
            String getEmail();
    }


}
