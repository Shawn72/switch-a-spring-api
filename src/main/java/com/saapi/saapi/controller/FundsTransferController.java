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
public class FundsTransferController {

	@Autowired
	FundsTrxService fndsTrxService;  
	
	@RequestMapping(value="/transfers", method=RequestMethod.POST)
    public FundsTransferTrx createTransfer(@RequestBody FundsTransferTrx trx) { 
        return fndsTrxService.createTransferTrx(trx);
    }
 	
 	@RequestMapping(value="/transfers", method=RequestMethod.GET)
    public List<FundsTransferTrx> readAllTransactions() {
        return fndsTrxService.getAllTransactions();
    }

    @RequestMapping(value="/transfers/{trxId}", method=RequestMethod.PUT)
    public FundsTransferTrx updateTransfer(@PathVariable(value = "trxId") Integer id, @RequestBody FundsTransferTrx trxDetails) {
        return fndsTrxService.updateTrx(id, trxDetails);
    }

    @RequestMapping(value="/transfers/{trxId}", method=RequestMethod.DELETE)
    public void deleteTransaction(@PathVariable(value = "trxId") Integer id) {
    	fndsTrxService.deleteTranx(id);
    }
    
    @GetMapping("/transfersbyid/{id}")
    public ResponseEntity<FundsTransferTrx> getTransactionById(@PathVariable(value = "id") Integer id )
     throws ResourceNotFoundException {     
    	FundsTransferTrx tranx =  fndsTrxService.getTranxById(id);
	      if(tranx!=null)  {
	    	  return ResponseEntity.ok().body(tranx);
	      }
	    	//runtime exception  
	    throw new ResourceNotFoundException("id not found : "+ id); 
    	    	
    }  
}
