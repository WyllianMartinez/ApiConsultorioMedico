package br.com.unipar.apiconsultoriomedico.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.unipar.apiconsultoriomedico.model.Medico;
import br.com.unipar.apiconsultoriomedico.repositories.PacienteRepository.PacienteProjection;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
	
	public List<Medico> findByNomeContainingAllIgnoringCase(String nome);
	
	@Query("SELECT m.nome AS nome, m.email AS email, m.crm AS crm, m.enumespecialidade AS especialidade FROM Medico m ORDER BY m.nome ASC")
    List<MedicoProjection> findAllByOrderByNomeAsc();

    interface MedicoProjection {
       String getNome();
       String getEmail();
       String getCrm();
       String getEspecialidade();
    }

}
