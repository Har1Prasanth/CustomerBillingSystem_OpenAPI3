package com.accenture.lkm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.accenture.lkm.entity.CustomerEntity;

public interface CustomerDAO extends CrudRepository<CustomerEntity, Integer> {

	@Query(name = "query1")
	List<CustomerEntity> findAllCustomersBycustomerName(String customerName);

	@Query(name = "query2")
	List<CustomerEntity> findAllCustomersByBillAmountBetween(double i, double j);
	
	//@Query(name="query1")
	@Query(value = "SELECT u FROM CustomerEntity u WHERE u.customerId = :id")
	CustomerEntity findOne(int id);

}
