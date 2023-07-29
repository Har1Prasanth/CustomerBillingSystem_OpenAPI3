package com.accenture.lkm.service;

import java.util.Collection;
import java.util.List;

import com.accenture.lkm.business.bean.CustomerBean;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
	CustomerBean addCustomer(CustomerBean bean) throws Exception;

	List<CustomerBean> getCustomerById(int id);

	List<CustomerBean> findAllCustomersBycustomerName(String customerName);

	Collection<CustomerBean> getAllCustomers();

	CustomerBean updateCustomer(CustomerBean customerBean);

	CustomerBean deleteCustomer(CustomerBean custBean);

	List<CustomerBean> findAllCustomersByBillAmountBetween(double i, double j);

	ResponseEntity<List<CustomerBean>> getCustomerApi(Integer id, String name, String type);
}
