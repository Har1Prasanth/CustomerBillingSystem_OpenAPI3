package com.accenture.lkm.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.CustomerBean;
import com.accenture.lkm.dao.CustomerDAOWrapper;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAOWrapper customerDAOWrapper;

	@Override
	public CustomerBean addCustomer(CustomerBean bean) throws Exception {

		return customerDAOWrapper.addEmployee(bean);
	}

	@Override
	public Collection<CustomerBean> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerDAOWrapper.getAllCustomers();
	}

	@Override
	public CustomerBean updateCustomer(CustomerBean customerBean) {
		return customerDAOWrapper.updateCustomer(customerBean);
	}

	@Override
	public CustomerBean deleteCustomer(CustomerBean custBean) {
		// TODO Auto-generated method stub
		return customerDAOWrapper.deleteCustomer(custBean);
	}

	@Override
	public CustomerBean getCustomerById(int id)  {
		return customerDAOWrapper.getCustomerById(id);
	}

	@Override
	public List<CustomerBean> findAllCustomersByBillAmountBetween(double i, double j) {

		return customerDAOWrapper.findAllCustomersByBillAmountBetween(i, j);
	}

	@Override
	public List<CustomerBean> findAllCustomersBycustomerNameStartsWith() {
		// TODO Auto-generated method stub
		return customerDAOWrapper.findAllCustomersBycustomerNameStartsWith();
	}

}
