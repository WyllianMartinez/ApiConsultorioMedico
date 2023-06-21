package br.com.unipar.apiconsultoriomedico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.unipar.apiconsultoriomedico.model.Cancelamento;

@Repository
public interface CancelamentoRepository  extends JpaRepository<Cancelamento, Long>{
	
	 

	}
