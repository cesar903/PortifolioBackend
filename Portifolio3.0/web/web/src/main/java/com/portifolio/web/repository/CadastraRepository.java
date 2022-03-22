package com.portifolio.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.portifolio.web.models.Cadastra;

public interface CadastraRepository extends CrudRepository<Cadastra, Long> {
	

	List<Cadastra> findById(String buscar);
	Cadastra findById(long id);


	// Query para a busca
	@Query(value = "select u from Cadastra u where u.nome like %?1%")
	List<Cadastra> findByNomes(String cadastra);
	Object findByEmail(String buscar);



	
}
