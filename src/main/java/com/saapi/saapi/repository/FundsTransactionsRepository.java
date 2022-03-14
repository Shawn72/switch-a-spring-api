package com.saapi.saapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.saapi.saapi.model.*;


@Repository
public interface FundsTransactionsRepository extends JpaRepository<FundsTransferTrx, Integer> {

}