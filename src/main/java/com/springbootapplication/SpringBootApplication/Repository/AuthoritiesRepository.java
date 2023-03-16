package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.springbootapplication.SpringBootApplication.Entity.Authorities;

@Transactional
public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {

	@Query(value = "select authority,id from Authorities u where u.usernamee.usernamee=?1")
	public List<String> listaAuthorities(String username);

	@Query(value = "from Authorities u where u.usernamee.usernamee=?1")
	public List<Authorities> listAuthorities(String username);

	@Query(value = "select distinct authority from  Authorities")
	public List<String> listaDistintAuthorities();

	@Modifying
	@Query(value = "delete from Authorities a where a.usernamee.id=:id")
	void deleteByAuthorities(@Param("id") int id);

}
