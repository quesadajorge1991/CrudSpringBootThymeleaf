package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springbootapplication.SpringBootApplication.Entity.GroupMembers;

@Transactional
public interface GroupMembersRepository extends JpaRepository<GroupMembers, Integer> {

	@Query(value = "from GroupMembers gm where gm.username=?1 and gm.groupId.id=?2")
	public List<GroupMembers> listaGroupMembers(String username, int id);

	@Query(value = "select distinct username from GroupMembers")
	public List<String> listaDistintAuthorities();

	@Query(value = "from GroupMembers g where g.username=?1")
	public List<GroupMembers> listaGroupMembers(String username);

	@Query(value = "from GroupMembers g where g.username=?1")
	public List<GroupMembers> listaMembersbyId(String username);

	@Modifying
	@Query(value = "delete from GroupMembers gm where gm.username=?1 and gm.groupId.id=?2")
	public void DeleteGroupMembers(String username, int id);

	@Query(value = "delete from group_members where username=?1", nativeQuery = true)
	public void DeleteUserGroupMembers(String username);

}
