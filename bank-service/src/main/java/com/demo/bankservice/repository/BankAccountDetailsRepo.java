package com.demo.bankservice.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.bankservice.entity.BankAccountDetails;

@Repository
public interface BankAccountDetailsRepo extends CrudRepository<BankAccountDetails, Long> {
	
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE BANK_ACCOUNT_DETAILS SET balance_amount = ?2 where account_number=?1",nativeQuery = true)
    public int updateAccountDetails(long accountNumber, double balance);
}
