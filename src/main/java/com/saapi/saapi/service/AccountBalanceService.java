package com.saapi.saapi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saapi.saapi.model.*;
import com.saapi.saapi.repository.*;

@Service
public class AccountBalanceService {
	  @Autowired
	  AccountBalanceRepository accbalRepository; 	  

	 // CREATE 
     public AccountBalances createAccountBalance(AccountBalances accBal) {
         return accbalRepository.save(accBal);
     }

     // READ
     public List<AccountBalances> getAllaccountBalances() {
         return accbalRepository.findAll();
     }

     // DELETE
     public void deleteAcountBalance(Integer accId) {
    	 accbalRepository.deleteById(accId);
     }
     
    // UPDATE
     public AccountBalances updateAccountBalance(Integer accBal, AccountBalances acBalDetails) {
    	 AccountBalances accBalls = accbalRepository.findById(accBal).get();
    	 // accBalls.setAccount_id(acBalDetails.getAccount_id());
    	 accBalls.setBalance(acBalDetails.getBalance());               
         return accbalRepository.save(accBalls);                                
     }
     
     //find by Id
     public AccountBalances getAccBalById(Integer id) {
     	  return accbalRepository.findById(id).get();
     }
  
}
