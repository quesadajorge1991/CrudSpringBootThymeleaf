package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.springbootapplication.SpringBootApplication.Entity.GroupAuthorities;

@Transactional /*
				 * si no se pone da error cuando se ejecuta una consulta de eliminar Ejemmplo
				 * del error Executing an update/delete query; nested exception is
				 * javax.persistence.TransactionRequiredException: Executing an update/delete
				 * query
				 */
public interface GroupAuthoritiesRepository extends JpaRepository<GroupAuthorities, Integer> {

	@Query(value = "select authority,id from GroupAuthorities g where g.groupId.id=?1")
	public List<String> listaGroupAuthorities(int id);

	@Query(value = "from GroupAuthorities g where g.groupId.id=?1")
	public List<GroupAuthorities> findGroupAuthoritiesByGroup(int id);

	@Modifying
	@Query(value = "delete from GroupAuthorities ga where ga.groupId.id=:id")
	void deleteByGroup(@Param("id") int groupId);
}
