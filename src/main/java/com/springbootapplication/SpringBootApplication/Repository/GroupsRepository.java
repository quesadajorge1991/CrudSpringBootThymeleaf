package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springbootapplication.SpringBootApplication.Entity.Groups;

public interface GroupsRepository extends JpaRepository<Groups, Integer> {

	@Query(value = "from Groups g where g.groupName=?1")
	public List<Groups> listaGroups(String username);

	@Query(value = "from Groups g where g.id=?1")
	public List<Groups> listaGroupById(int id);

	@Query(value = "from Groups g where g.id=?1")
	public Groups findById(int id);

	@Query(value = "from Groups")
	public List<Groups> findAllGroups();

	@Query(value = "from Groups g where g.groupName=?1")
	public Groups findByGroupName(String groupName);

}
