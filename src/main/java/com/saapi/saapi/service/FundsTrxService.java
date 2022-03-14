package com.saapi.saapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saapi.saapi.model.AccountBalances;
import com.saapi.saapi.model.FundsTransferTrx;
import com.saapi.saapi.model.Users;
import com.saapi.saapi.repository.*;

@Service
public class FundsTrxService {

 @Autowired
 FundsTransactionsRepository trxRepository;  
 
 public FundsTrxService(FundsTransactionsRepository tranxRepository) {
     this.trxRepository = tranxRepository;
 }
 
 // CREATE 
 public FundsTransferTrx createTransferTrx(FundsTransferTrx funds) {
     return trxRepository.save(funds);
 }

 // READ
 public List<FundsTransferTrx> getAllTransactions() {
     return trxRepository.findAll();
 }

 // DELETE
 public void deleteTranx(Integer trxId) {
	 trxRepository.deleteById(trxId);
 }
 
// UPDATE
 public FundsTransferTrx updateTrx(Integer trxId, FundsTransferTrx trxDetails) {
	 FundsTransferTrx tranx = trxRepository.findById(trxId).get();
	 tranx.setAccount_from(trxDetails.getAccount_from());
	 tranx.setAccount_to(trxDetails.getAccount_to());
	 tranx.setAmount(trxDetails.getAmount());                      
     return trxRepository.save(tranx);                                
 }
//find by Id
 public FundsTransferTrx getTranxById(Integer id) {
 	  return trxRepository.findById(id).get();
 } 
 
}
