package com.accenture.lkm.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.lkm.business.bean.CustomerBean;
import com.accenture.lkm.entity.CustomerEntity;

@Repository
public class CustomerDAOWrapper {

	@Autowired
	private CustomerDAO customerDAO;

	public CustomerBean addEmployee(CustomerBean bean) throws Exception {
		CustomerBean result = null;
		try {
			CustomerEntity custEntity = convertCustomerBeanToEntity(bean);
			CustomerEntity custEntity2 = customerDAO.save(custEntity);
			result = convertCustomerEntityToBean(custEntity2);
		} catch (Exception ex) {
			throw ex;
		}
		try {

		} catch (Exception ex) {
			throw ex;
		}
		return result;
	}
	
	public Collection<CustomerBean> getAllCustomers() {

		Iterable<CustomerEntity> entities = customerDAO.findAll();
		List<CustomerBean> customers = new ArrayList<CustomerBean>();
		for (CustomerEntity entity : entities) {
			customers.add(convertCustomerEntityToBean(entity));
		}
		return customers;
	}

	
	public CustomerBean updateCustomer(CustomerBean customerBean) {
		CustomerEntity entity = customerDAO.save(convertCustomerBeanToEntity(customerBean));
		return convertCustomerEntityToBean(entity);
	}
	
	public CustomerBean deleteCustomer(CustomerBean custBean) {
		CustomerEntity entity = convertCustomerBeanToEntity(custBean);
		customerDAO.delete(entity);
		return convertCustomerEntityToBean(entity);
	}

	public CustomerBean getCustomerById(int id) {
		CustomerBean result = null;
		try {
			CustomerEntity entity = customerDAO.findOne(id);
			if (entity != null) {
				result = convertCustomerEntityToBean(entity);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return result;
	}

	public List<CustomerBean> findAllCustomersByBillAmountBetween(double i, double j) {

		List<CustomerBean> custList = new ArrayList<CustomerBean>();

		List<CustomerEntity> entities = customerDAO.findAllCustomersByBillAmountBetween(i, j);
		for (CustomerEntity customerEntity : entities) {
			custList.add(convertCustomerEntityToBean(customerEntity));

		}
		return custList;
	}

	public List<CustomerBean> findAllCustomersBycustomerNameStartsWith() {

		List<CustomerBean> custList = new ArrayList<CustomerBean>();

		List<CustomerEntity> entities = customerDAO.findAllCustomersBycustomerNameStartsWith();

		for (CustomerEntity customerEntity : entities) {
			custList.add(convertCustomerEntityToBean(customerEntity));

		}
		return custList;

	}

	public static CustomerBean convertCustomerEntityToBean(CustomerEntity entity) {
		CustomerBean customer = new CustomerBean();
		BeanUtils.copyProperties(entity, customer);
		return customer;
	}

	public static CustomerEntity convertCustomerBeanToEntity(CustomerBean bean) {
		CustomerEntity entity = new CustomerEntity();
		BeanUtils.copyProperties(bean, entity);
		return entity;
	}

	

	
	

	

}
