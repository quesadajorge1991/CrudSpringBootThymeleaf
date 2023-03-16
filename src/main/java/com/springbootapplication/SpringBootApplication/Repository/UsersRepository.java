package com.springbootapplication.SpringBootApplication.Repository;


import com.springbootapplication.SpringBootApplication.Entity.Users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UsersRepository extends JpaRepository<Users, Integer> {

	@Query("FROM Users u WHERE u.usernamee=?1")
	Users getUser(String username);
	

	@Query("FROM Users u WHERE u.usernamee=?1")
	List<Users> getUsers(String username);

	
	@Query("FROM Users u WHERE u.usernamee=?1")
	Optional<Users> findByusername(String username);

	
	
	// @Modifying
	// @Query("update Users u set u.nombre=?2,
	// u.apellidos=?3,u.password=?4,u.email=?5, u.enabled=?6,u.descripcion=?7 where
	// u.username=?1")
	// void updateUser(String username,String nombre,String apellidos ,String
	// password,boolean enabled,String descripcion);

}
