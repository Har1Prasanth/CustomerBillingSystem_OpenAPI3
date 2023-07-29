package com.accenture.lkm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.accenture.lkm.Exception.RecordNotFoundException;
import com.accenture.lkm.dao.DiscountDAOWrapper;
import com.accenture.lkm.entity.DiscountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.CustomerBean;
import com.accenture.lkm.dao.CustomerDAOWrapper;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAOWrapper customerDAOWrapper;

	@Autowired
	private DiscountDAOWrapper discountDAOWrapper;

	@Override
	public CustomerBean addCustomer(CustomerBean bean) throws Exception {

		DiscountEntity discountEntity=null;
		discountEntity=discountDAOWrapper.getDiscountDetails(bean.getPromoCode());
		if(discountEntity!=null)
		{
			if(discountValidator(discountEntity.getValidity())){
			double v = bean.getBillAmount()-(bean.getBillAmount() * discountEntity.getDiscountAvailable() / 100.0);
			bean.setFinalPrice(v);}
			else {
				bean.setFinalPrice(bean.getBillAmount());
			}
		}

		return customerDAOWrapper.addEmployee(bean);
	}

	private boolean discountValidator(LocalDate validity) {
		if(validity.isAfter(LocalDate.now()))
			return true;
		else
			return false;
	}

	@Override
	public Collection<CustomerBean> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerDAOWrapper.getAllCustomers();
	}

	@Override
	public CustomerBean updateCustomer(CustomerBean customerBean) {
		DiscountEntity discountEntity=null;
		discountEntity=discountDAOWrapper.getDiscountDetails(customerBean.getPromoCode());
		if(discountEntity!=null)
		{
			double v = customerBean.getBillAmount()-(customerBean.getBillAmount() * discountEntity.getDiscountAvailable() / 100.0);
			customerBean.setFinalPrice(v);
		}

		return customerDAOWrapper.updateCustomer(customerBean);
	}

	@Override
	public CustomerBean deleteCustomer(CustomerBean custBean) {
		// TODO Auto-generated method stub
		return customerDAOWrapper.deleteCustomer(custBean);
	}

	@Override
	public List<CustomerBean> getCustomerById(int id)  {

		return customerDAOWrapper.getCustomerById(id);
	}

	@Override
	public List<CustomerBean> findAllCustomersByBillAmountBetween(double i, double j) {

		return customerDAOWrapper.findAllCustomersByBillAmountBetween(i, j);
	}

	@Override
	public List<CustomerBean> findAllCustomersBycustomerName(String customerName) {
		// TODO Auto-generated method stub
		return customerDAOWrapper.findAllCustomersBycustomerName(customerName);
	}

	@Override
	public ResponseEntity<List<CustomerBean>> getCustomerApi(Integer id, String name, String type) {
		if ((type!=null && type.equalsIgnoreCase("id")&&name!=null) || (type!=null && type.equalsIgnoreCase("name")&&id!=null)||
				(type==null&&name!=null) || (type==null&&id!=null)) {
			throw new RecordNotFoundException("Bad Request,Check the headers and params");

		}else if(type!=null && type.equalsIgnoreCase("name")&&name!=null){
			List<CustomerBean> custBean = customerDAOWrapper.findAllCustomersBycustomerName(name);
			if (custBean.size() > 0)
				return new ResponseEntity<List<CustomerBean>>(custBean, HttpStatus.OK);
			else
				throw new RecordNotFoundException("Customer with Name " + name + " not found.");
		} else if(type!=null && type.equalsIgnoreCase("id") && id.intValue()>0){

			List<CustomerBean> custBean=customerDAOWrapper.getCustomerById(id);
			if (custBean.size()>0)
				return new ResponseEntity<List<CustomerBean>>(custBean, HttpStatus.OK);
			else
				throw new RecordNotFoundException("Invalid Customer ID: " + id);
		} else{
			List<CustomerBean> custList = new ArrayList<CustomerBean>(customerDAOWrapper.getAllCustomers());
			if (custList.size() > 0)
				return new ResponseEntity<List<CustomerBean>>(custList, HttpStatus.OK);
			else
				throw new RecordNotFoundException("No Customer Bills found!!");
		}
	}
}
