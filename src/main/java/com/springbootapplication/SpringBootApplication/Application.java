package com.springbootapplication.SpringBootApplication;

import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springbootapplication.SpringBootApplication.Entity.Authorities;
import com.springbootapplication.SpringBootApplication.Entity.Users;
import com.springbootapplication.SpringBootApplication.Repository.AuthoritiesRepository;
import com.springbootapplication.SpringBootApplication.Repository.UsersRepository;
import com.springbootapplication.SpringBootApplication.SecurityConfig.EncodePass;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	AuthoritiesRepository authoritiesRepository;

	@PostConstruct
	public void insertBD() {

		List<Users> user = usersRepository.findAll();

		/* pregnta si existe el usuario en la bd */
		if (user.isEmpty()) {
			usersRepository.save(
					new Users("admin", EncodePass.codificarPass("admin"), true, "Admiistrador full del sistema"));
			String authorities[] = new String[] { "ROLE_ADMIN", "ROLE_EMPLOYE","ROLE_DEPARTMENT" };
			for (int i = 0; i < authorities.length; i++) {
				authoritiesRepository
						.save(new Authorities(authorities[i], new Users(usersRepository.getUser("admin").getId())));
			}

		} else {
			/* añade las provincias si no existen */

		}
	}

}
