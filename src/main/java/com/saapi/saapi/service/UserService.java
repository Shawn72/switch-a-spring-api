package com.saapi.saapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saapi.saapi.model.Users;
import com.saapi.saapi.repository.UserRepository;

@Service
public class UserService {

        @Autowired
            UserRepository usrRepository;  
        
        // CREATE 
        public Users createUser(Users user) {
            return usrRepository.save(user);
        }

        // READ
        public List<Users> getUsers() {
            return usrRepository.findAll();
        }

        // DELETE
        public void deleteUser(Integer userId) {
        	usrRepository.deleteById(userId);
        }
        
       // UPDATE
        public Users updateUser(Integer userId, Users userDetails) {
                Users user = usrRepository.findById(userId).get();
                user.setFname(userDetails.getFname());
                user.setLname(userDetails.getLname());
                user.setEmail(userDetails.getEmail());
                user.setPassword(userDetails.getPassword());                
                return usrRepository.save(user);                                
        }
        
        //find by Id
        public Users getUserById(Integer id) {
        	  return usrRepository.findById(id).get();
        }
}
