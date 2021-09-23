package br.com.letmewalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.letmewalk.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
