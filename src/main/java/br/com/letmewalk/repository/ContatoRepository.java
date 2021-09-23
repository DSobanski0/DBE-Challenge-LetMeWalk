package br.com.letmewalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.letmewalk.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

	List<Contato> findByUserId(Long userId);

	List<Contato> findAllByUserId(Long userId);

}
