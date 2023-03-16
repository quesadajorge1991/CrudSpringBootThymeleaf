package com.springbootapplication.SpringBootApplication.Controllers;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootapplication.SpringBootApplication.Entity.Authorities;
import com.springbootapplication.SpringBootApplication.Entity.Users;
import com.springbootapplication.SpringBootApplication.Repository.*;
@Controller
@RequestMapping("/user")
public class UsersController {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	AuthoritiesRepository authoritiesRepository;

	public static String encodePassword(String password) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	@GetMapping("/resetpass")
	public String resetpass(Model model) {
		model.addAttribute("users", usersRepository.findAll());
		return "Users/resetpass";
	}

	@GetMapping(value = "/getUsers")
	public String getUsers(Model model) {
		List<Users> usuarios = usersRepository.findAll();
		model.addAttribute("users", usuarios);
		return "templateBase/ComponentFragment :: users";

	}

	@PostMapping("/resetPassword")
	public @ResponseBody String resetPassword(@RequestParam(value = "username") int username,
			@RequestParam(value = "password") String password) {
		JSONObject jsono = new JSONObject();

		Users user = usersRepository.findById(username).get();

		System.err.println(user.getUsernamee());

		try {
			Users usuario = usersRepository.findById(username).get();
			usuario.setPassword(encodePassword(password));
			usuario.setEnabled(user.isEnabled());
			usuario.setDescripcion(user.getDescripcion());

			usersRepository.save(usuario);

			jsono.put("msgtype", "success");
			jsono.put("msgtitle", "Información");
			jsono.put("msgbody", "Se actualizó la contraseña al usuario" + user.getUsernamee());
		} catch (Exception e) {
			// TODO: handle exception
			jsono.put("msgtype", "error");
			jsono.put("msgtitle", "Error");
			jsono.put("msgbody", "Error al actualizar la contraseña al usuario" + user.getUsernamee());
		}

		return jsono.toString();
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/users")
	public String mostrarUsuarios(Model model) {
		List<Users> users = usersRepository.findAll();
		model.addAttribute("listusuarios", users);
		return "Users/users";
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/addUser")
	public String addUsuario(Model model) {
		model.addAttribute("user", new Users());
		return "Users/addUser";
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/delete_user/{id}")
	public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

		try {
			usersRepository.deleteById(id);
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Usuario eleminado correctamente ");
			return "redirect:/user/users";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al eleminar el usuario " + e.getLocalizedMessage());
		}

		return "redirect:/user/users";
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/add_User")
	public String add_Usuario(@ModelAttribute("users") Users user, RedirectAttributes redirectAttributes) {

		try {

			Users usuario = new Users(user.getUsernamee(), encodePassword(user.getPassword()), user.isEnabled(),
					user.getDescripcion());
			usersRepository.save(usuario);
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Usuario agregado correctamente ");
			return "redirect:/user/users";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al agregar el usuario " + e.getLocalizedMessage());
		}

		return "redirect:/user/users";
	}

	String tempuser = "";

	// @PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/updateUser/{id}")
	public String update(Model model, @PathVariable(value = "id") int id) {
		Users usuarios = usersRepository.findById(id).get();
		model.addAttribute("usuario", usuarios);
		tempuser = usuarios.getUsernamee();
		return "Users/updateUser";
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/update_user")
	public String update_Usuario(@ModelAttribute("usuario") Users user, RedirectAttributes redirectAttributes) {

		try {
			System.err.println(user.getUsernamee());
			System.out.println(usersRepository.getUsers(user.getUsernamee()).size());
			List<Users> list = usersRepository.getUsers(user.getUsernamee());

			System.out.println(user.getUsernamee());
			if (list.size() == 0 || (user.getUsernamee().contentEquals(tempuser))) {

				Users usuario = usersRepository.findById(user.getId()).get();
				usuario.setUsernamee(user.getUsernamee());
				usuario.setEnabled(user.isEnabled());
				usuario.setDescripcion(user.getDescripcion());
				usersRepository.save(usuario);
				redirectAttributes.addFlashAttribute("msgbody",
						"Usuario " + usuario.getUsernamee() + " modificado correctamente");
				redirectAttributes.addFlashAttribute("msgtipo", "success");
				redirectAttributes.addFlashAttribute("msgtitu", "Error");

			} else {
				redirectAttributes.addFlashAttribute("msgbody", "El nombre de usuario ya existe");
				redirectAttributes.addFlashAttribute("msgtipo", "error");
				redirectAttributes.addFlashAttribute("msgtitu", "Error");
			}

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgbody", "Error al guardar los datos");
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
		}

		return "redirect:/user/users";
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/addPermisos")
	public String addPermisos(Model model) {
		List<Users> users = usersRepository.findAll();
		model.addAttribute("listusuarios", users);
		return "Authorities/addPermisos";
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/getPermisos", produces = "application/json")
	public @ResponseBody List<Authorities> getPermisos(@RequestParam("id") int id) {
		Users user = usersRepository.findById(id).get();
		return user.getAuthoritiesList();

	}

	// @PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/getPermisoss")
	public String getAuthorityUser(@RequestParam("usuario") int id, Model model) {
		Users user = usersRepository.findById(id).get();
		model.addAttribute("listusers", user);
		model.addAttribute("listauthorities", user.getAuthoritiesList());
		// model.addAttribute("listaAthoritiesInMemory",
		// permisosInMemory.getListinmemory());

		String permiso;
		for (int i = 0; i < user.getAuthoritiesList().size(); i++) {

			permiso = user.getAuthoritiesList().get(i).getAuthority();
			System.out.println(permiso);

			switch (permiso) {

			case "ROLE_ADMIN":
				if (!permiso.equalsIgnoreCase("ROLE_ADMIN")) {
					model.addAttribute("ROLE_ADMIN", false);

				} else {
					model.addAttribute("ROLE_ADMIN", true);
				}

			case "ROLE_EMPLOYE":
				if (!permiso.equalsIgnoreCase("ROLE_EMPLOYE")) {
					model.addAttribute("ROLE_EMPLOYE", false);

				} else {
					model.addAttribute("ROLE_EMPLOYE", true);
				}

			case "ROLE_DEPARTMENT":
				if (!permiso.equalsIgnoreCase("ROLE_DEPARTMENT")) {
					model.addAttribute("ROLE_DEPARTMENT", false);

				} else {
					model.addAttribute("ROLE_DEPARTMENT", true);
				}

			default: // model.addAttribute("ROLE_UPDATE", false); }

			}

		}
		return "templateBase/ComponentFragment :: contentAutorities";
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/addAuthorityToUser")
	public String addAuthorityToUser(@RequestParam(value = "usuario") int id,
			@RequestParam(value = "authorities[]", defaultValue = "") String authorities[],
			RedirectAttributes redirectAttrs, Model model) throws JSONException {

		try {
			Users usuario = usersRepository.findById(id).get();
			System.err.println(usuario.getId());

			authoritiesRepository.deleteByAuthorities(usuario.getId());
			/* Para eliminar todos los permisos a el usuario */

			for (int i = 0; i < authorities.length; i++) {
				// verificar si existe el permiso que se kiere aÃ±adir

				String temp = authorities[i];

				// aÃ±adir el permiso
				try {
					System.err.println(temp);
					authoritiesRepository.save(new Authorities(authorities[i], usuario));
					redirectAttrs.addFlashAttribute("msgbody",
							"Se han insertado correctamente los permisos seleccionados al usuario "
									+ usuario.getUsernamee());
					redirectAttrs.addFlashAttribute("msgtitu", "Confirmacion");
					redirectAttrs.addFlashAttribute("msgtipo", "success");

				} catch (Exception e) {
					redirectAttrs.addFlashAttribute("msgbody", "Error al insertar permisos" + usuario.getUsernamee());
					redirectAttrs.addFlashAttribute("msgtitu", "Error");
					redirectAttrs.addFlashAttribute("msgtipo", "error");
				}

			}
			// Si la lista de permisos esta vacia muestra estos mensajes
			try {

				redirectAttrs.addFlashAttribute("msgbody",
						"Se han insertado correctamente los permisos seleccionados al usuario "
								+ usuario.getUsernamee());
				redirectAttrs.addFlashAttribute("msgtitu", "Confirmacion");
				redirectAttrs.addFlashAttribute("msgtipo", "success");

			} catch (Exception e) {
				redirectAttrs.addFlashAttribute("msgbody", "Error al insertar permisos" + usuario.getUsernamee());
				redirectAttrs.addFlashAttribute("msgtitu", "Error");
				redirectAttrs.addFlashAttribute("msgtipo", "error");
			}

		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("msgbody", "Error al intentar añadir los permisos ");
			redirectAttrs.addFlashAttribute("msgtitu", "Error");
			redirectAttrs.addFlashAttribute("msgtipo", "error");

		}

		return "redirect:/user/addPermisos";
	}

}
