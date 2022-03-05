package com.saapi.saapi.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.saapi.saapi.exceptions.ResourceNotFoundException;
import com.saapi.saapi.model.Users;
import com.saapi.saapi.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService usrService;   
    
    @RequestMapping(value="/users", method=RequestMethod.POST)
    public Users createAUser(@RequestBody Users user) throws InvalidKeySpecException, NoSuchAlgorithmException {
    	String salt = generateSalt();
    	
    	//encrypt password before saving in dB
    	String EncPasswd = SHA_1_EncryptedPassword( user.getPassword(), salt );   
    	
    	//set the encrypted password
    	user.setPassword(EncPasswd );
    	
        return usrService.createUser(user);
    }
    
    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<Users> readUsers() {
        return usrService.getUsers();
    }

    @RequestMapping(value="/users/{userId}", method=RequestMethod.PUT)
    public Users updateAUser(@PathVariable(value = "userId") Integer id, @RequestBody Users userDetails) {
        return usrService.updateUser(id, userDetails);
    }

    @RequestMapping(value="/users/{userId}", method=RequestMethod.DELETE)
    public void deleteAUser(@PathVariable(value = "userId") Integer id) {
    	usrService.deleteUser(id);
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUsersById(@PathVariable(value = "id") Integer id )
     throws ResourceNotFoundException {     
	      Users user =  usrService.getUserById(id);
	      if(user!=null)  {
	    	  return ResponseEntity.ok().body(user);
	      }
	    	//runtime exception  
	    throw new ResourceNotFoundException("id not found : "+ id); 
    	    	
    } 
    
    //encrypt password using salting technology
    private static String SHA_1_EncryptedPassword(String passwordStr, String salt) {
        String saltedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordStr.getBytes());
            StringBuilder sb = new StringBuilder();
        	for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        	}
            saltedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return saltedPassword;
    }
    
    // generate salt
    private static String generateSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }
   

}
