package com.accenture.lkm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.accenture.lkm.Exception.CustomExceptionHandler;
import com.accenture.lkm.Exception.RecordNotFoundException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.accenture.lkm.business.bean.CustomerBean;
import com.accenture.lkm.service.CustomerService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags = "CustomerBillingSystem")
@ApiOperation(value = "CustomerBillingSystem",tags = "CustomerBillingSystem")
public class CustomerBillingSystem {

	@Autowired
	CustomerService customerService;


	@ApiOperation(value = "Add a new Customer Bill", nickname = "addBill", notes = "", authorizations = {
	}, tags={ "CustomerBillingSystem", })
	@PostMapping(value = "api/v2/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addCustomer( @Valid @RequestBody CustomerBean billDetails,@ApiIgnore Errors err) throws Exception {

		if (err.hasErrors()){
			//return new ResponseEntity<String>(err.getAllErrors());
			String errors=err.getAllErrors().toString();
		throw new RecordNotFoundException(errors);
		}
		else {

			CustomerBean inserted = customerService.addCustomer(billDetails);
			return new ResponseEntity<String>("Customer Bill added successfully with id :" + inserted.getCustomerId(),
					HttpStatus.CREATED);
		}
	}

	@ApiOperation(value = "Update the Customer Bill", nickname = "updateBill", notes = "", authorizations = {
	}, tags={ "CustomerBillingSystem", })
	@PutMapping(value = "api/v2/customer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerBean> updateCustomer(@RequestBody CustomerBean billDetails) throws Exception {
		CustomerBean custBean = customerService.getCustomerById(billDetails.getCustomerId());
		if (custBean != null) {
			CustomerBean updated = customerService.updateCustomer(billDetails);
			return new ResponseEntity<CustomerBean>(updated, HttpStatus.OK);
		} else
			//return new ResponseEntity<CustomerBean>(HttpStatus.NOT_FOUND);
			throw new RecordNotFoundException("Customer ID: " + billDetails.getCustomerId()+" not found to Update.");
	}

	@ApiOperation(value = "Delete a Customer Bill", nickname = "deleteBill", notes = "", authorizations = {
	}, tags={ "CustomerBillingSystem", })
	@DeleteMapping(value = "api/v2/cid/{customerID}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCustomerById(@PathVariable("customerID") int customerID) {
		CustomerBean custBean = customerService.getCustomerById(customerID);
		if (custBean != null) {
			CustomerBean deleted = customerService.deleteCustomer(custBean);
			return ResponseEntity.ok().body("Customer deleted successfully\nCustomerID :" + deleted.getCustomerId()+"\nCustomer Name:"+deleted.getCustomerName());
		} else
			throw new RecordNotFoundException("Customer ID:" +customerID+" not found to Delete.");

	}




	@ApiOperation(value = "Get a list of Customer Bills", nickname = "getCustomers", notes = "", authorizations = {
	}, tags={ "CustomerBillingSystem", })
	@GetMapping(value = "api/v2/customers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerBean>> getAllCustomers() {
		List<CustomerBean> custList = new ArrayList<CustomerBean>(customerService.getAllCustomers());
		if (custList.size() > 0)
			return new ResponseEntity<List<CustomerBean>>(custList, HttpStatus.OK);
		else
			//return new ResponseEntity<List<CustomerBean>>(HttpStatus.NOT_FOUND);
			throw new RecordNotFoundException("No Customer Bills found!!");
	}

	@ApiOperation(value = "Get a Customer bill", nickname = "getBill", notes = "", authorizations = {
	}, tags={ "CustomerBillingSystem", })
	@GetMapping(value = "api/v2/id/{customerID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerBean> getCustomerById(@PathVariable("customerID") int customerID) {
		CustomerBean custBean = customerService.getCustomerById(customerID);
		if (custBean != null)
			return new ResponseEntity<CustomerBean>(custBean, HttpStatus.OK);
		else
			//throw new CustomerNotFoundException(customerID);
				throw new RecordNotFoundException("Invalid Customer ID: " + customerID);

	}



	@ApiOperation(value = "Get CustomerBill based on Name", nickname = "customerName", notes = "", authorizations = {
	}, tags={ "CustomerBillingSystem", })
	@GetMapping(value = "api/v2/name/{customerName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerBean>> getCustomerByName(@PathVariable("customerName") String customerName) {
		//this.customerName = customerName;
		List<CustomerBean> custBean = customerService.findAllCustomersBycustomerName(customerName);
		if (custBean.size() > 0)
			return new ResponseEntity<List<CustomerBean>>(custBean, HttpStatus.OK);
		else
			//return new ResponseEntity<List<CustomerBean>>(HttpStatus.NOT_FOUND);
			throw new RecordNotFoundException("Customer with Name " + customerName+" not found.");
	}


	@ApiOperation(value = "Get Customers in the Bill Amount Range", nickname = "BillRange", notes = "", authorizations = {
	}, tags={ "CustomerBillingSystem", })
	@GetMapping(value = "api/v2/{BillAmount1}/{BillAmount2}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerBean>> getCustomerByBillAmount(@PathVariable("BillAmount1") double BillAmount1, @PathVariable("BillAmount2") double BillAmount2) {
		List<CustomerBean> custBean = customerService.findAllCustomersByBillAmountBetween(BillAmount1, BillAmount2);
		if (custBean.size() > 0)
			return new ResponseEntity<List<CustomerBean>>(custBean, HttpStatus.OK);
		else
			throw new RecordNotFoundException("Customer Bills not found between "+BillAmount1+" to "+BillAmount2);
	}



}
