package com.springbootapplication.SpringBootApplication.Controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springbootapplication.SpringBootApplication.Entity.Users;
import com.springbootapplication.SpringBootApplication.Repository.UsersRepository;
import com.springbootapplication.SpringBootApplication.SecurityConfig.EncodePass;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@Autowired
	UsersRepository usersRepository;

	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}

	@GetMapping("/page403")
	public String page403() {
		return "page403";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/profile")
	public String prueba(Model model) {
		model.addAttribute("users", usersRepository.findAll());
		return "profile";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		model.addAttribute("users", usersRepository.findAll());
		return "home";
	}

	// @PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/changePassword/{username}")
	public @ResponseBody String changePasswordProfile(@PathVariable("username") String username,
			@RequestParam(value = "password") String password, @RequestParam(value = "password1") String password1,
			@RequestParam(value = "passwordold") String passwordold) {

		JSONObject jsono = new JSONObject();

		try {

			if (!(password.isBlank() || password1.isBlank() || passwordold.isBlank())) {

				// validar que exista
				if (password.contentEquals(password1)) {
					Users user = usersRepository.getUser(username);

					if (EncodePass.matches(passwordold, user.getPassword())) {
						usersRepository.save(new Users(user.getId(), user.getUsernamee(),
								EncodePass.codificarPass(password), user.isEnabled(), user.getDescripcion()));
						jsono.put("msgtype", "success");
						jsono.put("msgtitle", "Información");
						jsono.put("msgbody", "Se actualizó correctamente la contraseña");
					} else {
						System.err.println(user.getPassword());
						System.err.println(EncodePass.codificarPass(passwordold));
						jsono.put("msgtype", "error");
						jsono.put("msgtitle", "Error");
						jsono.put("msgbody", "La contraseña anterior no coincide");

					}

				} else {
					jsono.put("msgtype", "error");
					jsono.put("msgtitle", "Error");
					jsono.put("msgbody", "Las contraseñas no coinciden");
				}

			} else {
				jsono.put("msgtype", "error");
				jsono.put("msgtitle", "Error");
				jsono.put("msgbody", "No deben quedar campos en blanco");
			}

		} catch (Exception e) {
			jsono.put("msgtype", "error");
			jsono.put("msgtitle", "Error");
			jsono.put("msgbody", "No deben quedar campos en blanco");
		}

		return jsono.toString();
	}

}
