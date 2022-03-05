package com.saapi.saapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saapi.saapi.model.*;
import com.saapi.saapi.repository.*;

@Service
public class FinancialAccountService {
	 @Autowired
     FinancialAccsRepository fncAccRepository;
	 
	 // CREATE 
     public FinancialAccounts createFinAccount(FinancialAccounts fnAcc) {
         return fncAccRepository.save(fnAcc);
     }

     // READ
     public List<FinancialAccounts> getAllFinAccounts() {
         return fncAccRepository.findAll();
     }

     // DELETE
     public void deleteFinAccount(Integer accId) {
    	 fncAccRepository.deleteById(accId);
     }
     
    // UPDATE
     public FinancialAccounts updateFinAccount(Integer accId, FinancialAccounts finaccDetails) {
    	 FinancialAccounts finacc = fncAccRepository.findById(accId).get();
    	 finacc.setUser_id(finaccDetails.getUser_id());
    	 finacc.setAccount_number(finaccDetails.getAccount_number());               
         return fncAccRepository.save(finacc);                                
     }
     
     //find by Id
     public FinancialAccounts getAccById(Integer id) {
     	  return fncAccRepository.findById(id).get();
     }

}
