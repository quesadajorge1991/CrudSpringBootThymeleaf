/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootapplication.SpringBootApplication.SecurityConfig;


//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

//import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author jorge
 */
public class EncodePass {

    public EncodePass() {
    }
    
    
    
    public static String codificarPass(CharSequence pass){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pass);
        
    }
    
  public static boolean matches(String rawPassword,String encodePassword){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encodePassword);
        
    }
    
    
    
    
    
    
    
    
  
}
