package com.saapi.saapi.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saapi.saapi.dtos.*;
import com.saapi.saapi.model.*;
import com.saapi.saapi.repository.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository usrRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("logged_in_success", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto regDto){

        // check if username exists in the Database    	
		  if(usrRepository.existsByUsername(regDto.getUsername())){
		   return new ResponseEntity<>("Username is already taken!, use another", HttpStatus.BAD_REQUEST);
		  }

        // check for email exists in the Database
		  if(usrRepository.existsByEmail(regDto.getEmail())){
			    return new ResponseEntity<>("That email is already taken!, use another", HttpStatus.BAD_REQUEST);
		  }

        // create user object
        Users user = new Users();
        user.setFname(regDto.getFirstname());
        user.setLname(regDto.getLastname());       
        user.setEmail(regDto.getEmail());
        user.setPassword(passwordEncoder.encode(regDto.getPassword()));
        user.setUsername(regDto.getUsername());

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        usrRepository.save(user);

        return new ResponseEntity<>("registered_success", HttpStatus.OK);

    }
}
