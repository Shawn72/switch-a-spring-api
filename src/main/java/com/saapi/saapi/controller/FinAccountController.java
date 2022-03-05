package com.saapi.saapi.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
public class FinAccountController {
	@Autowired
	FinancialAccountService finaccService;  
	 
 	@RequestMapping(value="/finaccounts", method=RequestMethod.POST)
    public FinancialAccounts createFinancialAccount(@RequestBody FinancialAccounts finacc) { 
        return finaccService.createFinAccount(finacc);
    }
 	
 	@RequestMapping(value="/finaccounts", method=RequestMethod.GET)
    public List<FinancialAccounts> readAllAccounts() {
        return finaccService.getAllFinAccounts();
    }

    @RequestMapping(value="/finaccounts/{accId}", method=RequestMethod.PUT)
    public FinancialAccounts updateFinAccount(@PathVariable(value = "accId") Integer id, @RequestBody FinancialAccounts accDetails) {
        return finaccService.updateFinAccount(id, accDetails);
    }

    @RequestMapping(value="/finaccounts/{accId}", method=RequestMethod.DELETE)
    public void deleteAUser(@PathVariable(value = "accId") Integer id) {
    	finaccService.deleteFinAccount(id);
    }
    
    @GetMapping("/finaccounts/{id}")
    public ResponseEntity<FinancialAccounts> getFinAccsById(@PathVariable(value = "id") Integer id )
     throws ResourceNotFoundException {     
    	FinancialAccounts finAcc =  finaccService.getAccById(id);
	      if(finAcc!=null)  {
	    	  return ResponseEntity.ok().body(finAcc);
	      }
	    	//runtime exception  
	    throw new ResourceNotFoundException("id not found : "+ id); 
    	    	
    }  

}
