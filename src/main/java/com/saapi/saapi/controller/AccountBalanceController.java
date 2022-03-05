package com.saapi.saapi.controller;

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
import com.saapi.saapi.model.*;
import com.saapi.saapi.service.*;

@RestController
@RequestMapping("/api")
public class AccountBalanceController {
	@Autowired
	AccountBalanceService accBalService;  
	
	@RequestMapping(value="/balances", method=RequestMethod.POST)
    public AccountBalances createAccountBalance(@RequestBody AccountBalances accBals) { 
        return accBalService.createAccountBalance(accBals);
    }
 	
 	@RequestMapping(value="/balances", method=RequestMethod.GET)
    public List<AccountBalances> readAllAccountBalances() {
        return accBalService.getAllaccountBalances();
    }

    @RequestMapping(value="/balances/{balId}", method=RequestMethod.PUT)
    public AccountBalances updateAccountBalance(@PathVariable(value = "balId") Integer id, @RequestBody AccountBalances balDetails) {
        return accBalService.updateAccountBalance(id, balDetails);
    }

    @RequestMapping(value="/balances/{balId}", method=RequestMethod.DELETE)
    public void deleteAccBalance(@PathVariable(value = "balId") Integer id) {
    	accBalService.deleteAcountBalance(id);
    }
    
    @GetMapping("/balances/{id}")
    public ResponseEntity<AccountBalances> getAccBalById(@PathVariable(value = "id") Integer id )
     throws ResourceNotFoundException {     
    	AccountBalances accBal =  accBalService.getAccBalById(id);
	      if(accBal!=null)  {
	    	  return ResponseEntity.ok().body(accBal);
	      }
	    	//runtime exception  
	    throw new ResourceNotFoundException("id not found : "+ id); 
    	    	
    }  


}
