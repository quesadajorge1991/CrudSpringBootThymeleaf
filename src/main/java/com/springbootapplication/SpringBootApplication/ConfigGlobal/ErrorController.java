package com.springbootapplication.SpringBootApplication.ConfigGlobal;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

//@ControllerAdvice
public class ErrorController {

	//@ExceptionHandler(Exception.class)
	//@ResponseStatus(HttpStatus.NOT_FOUND)
	public String exception(Model model,Exception exception) {
		model.addAttribute("exception", exception);
		//ModelAndView mav=new ModelAndView();
		//mav.addObject("exception", "sdfsdfsdfsdfsdf");
		return "404";

	}

}
