package com.demo.paymentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.demo.paymentservice.entity.PaymentApiTransaction;

public interface PaymentApiTransactionRepository extends CrudRepository<PaymentApiTransaction, Integer> {
	
	@Query(value="select * from PAYMENT_API_TRANSACTION where ACCOUNT_NUMBER=?1  and ROWNUM <=5 order by TRANSACTION_DATE ",nativeQuery=true)
	public List<PaymentApiTransaction> findHistory(Long accountNumber);
}
