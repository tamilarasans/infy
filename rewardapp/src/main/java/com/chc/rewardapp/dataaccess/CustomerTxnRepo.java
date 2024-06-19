package com.chc.rewardapp.dataaccess;

import java.util.List;


import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.chc.rewardapp.model.CustomerTxns;

@Repository
public interface CustomerTxnRepo extends CrudRepository <CustomerTxns, Long>{
	public List<CustomerTxns> findByCustomerIdOrderByPurchaseDateDesc(Integer customerId);
}
