package com.markpedev.shopping.userapi.repository;

import org.springframework.stereotype.Repository;

import com.markpedev.shopping.userapi.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByCpf(String cpf);
	
	List<User> queryByNomeLike(String name);
}
