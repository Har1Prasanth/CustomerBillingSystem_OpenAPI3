package com.accenture.lkm.service;

import java.util.Collection;
import java.util.List;

import com.accenture.lkm.business.bean.CustomerBean;
public interface CustomerService {
	CustomerBean addCustomer(CustomerBean bean) throws Exception;

	CustomerBean getCustomerById(int id);

	List<CustomerBean> findAllCustomersBycustomerNameStartsWith();

	Collection<CustomerBean> getAllCustomers();

	CustomerBean updateCustomer(CustomerBean customerBean);

	CustomerBean deleteCustomer(CustomerBean custBean);

	List<CustomerBean> findAllCustomersByBillAmountBetween(double i, double j);
}
