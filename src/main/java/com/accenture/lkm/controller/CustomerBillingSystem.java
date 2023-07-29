package com.accenture.lkm.controller;

import com.accenture.lkm.Exception.RecordNotFoundException;
import com.accenture.lkm.business.bean.CustomerBean;
import com.accenture.lkm.service.CustomerService;
import com.accenture.lkm.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerBillingSystem {

    @Autowired
    CustomerService customerService;


    @Operation(summary = "Add a new Customer Bill",tags={"CustomerBillingSystem"})
    @PostMapping(value = "api/v2/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerBean billDetails, Errors err) throws Exception {

        if (err.hasErrors()) {
            //return new ResponseEntity<String>(err.getAllErrors());
            String errors = err.getAllErrors()
                    .toString();
            throw new RecordNotFoundException(errors);
        } else {

            CustomerBean inserted = customerService.addCustomer(billDetails);

            return new ResponseEntity<String>("Final Price: "+inserted.getFinalPrice(), HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Update the Customer Bill",tags = {"CustomerBillingSystem"})
    @PutMapping(value = "api/v2/customer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerBean> updateCustomer(@RequestBody CustomerBean billDetails) throws Exception {
        List<CustomerBean> custBean = customerService.getCustomerById(billDetails.getCustomerId());
        if (custBean.size()>0) {
            CustomerBean updated = customerService.updateCustomer(billDetails);
            return new ResponseEntity<CustomerBean>(updated, HttpStatus.OK);
        } else
            //return new ResponseEntity<CustomerBean>(HttpStatus.NOT_FOUND);
            throw new RecordNotFoundException("Customer ID: " + billDetails.getCustomerId() + " not found to Update.");
    }

    @Operation(summary = "Delete a Customer Bill",tags = {"CustomerBillingSystem"})
    @DeleteMapping(value = "api/v2/cid/{customerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCustomerById(@PathVariable("customerID") int customerID) {
        List<CustomerBean> custBean = customerService.getCustomerById(customerID);
        if (custBean.size()>0) {
            CustomerBean deleted=customerService.deleteCustomer(custBean.get(0));
            return ResponseEntity.ok().body("Customer deleted successfully\nCustomerID :" + deleted.getCustomerId() + "\nCustomer Name:" + deleted.getCustomerName());
        } else throw new RecordNotFoundException("Customer ID:" + customerID + " not found to Delete.");

    }

    @Operation(summary = "Delete a Customer Bill",tags = {"CustomerBillingSystem"})
    //  @GetMapping(value = "api/v2/customersList", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.MyResponseViews.class)
    public ResponseEntity<List<CustomerBean>> getAllCustomers() {
        List<CustomerBean> custList = new ArrayList<CustomerBean>(customerService.getAllCustomers());
        if (custList.size() > 0) return new ResponseEntity<List<CustomerBean>>(custList, HttpStatus.OK);
        else
            //return new ResponseEntity<List<CustomerBean>>(HttpStatus.NOT_FOUND);
            throw new RecordNotFoundException("No Customer Bills found!!");
    }

    //  @GetMapping(value = "api/v2/id/{customerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.MyResponseViews.class)
    public ResponseEntity<List<CustomerBean>> getCustomerById(@PathVariable("customerID") int customerID) {
        List<CustomerBean> custBean = customerService.getCustomerById(customerID);
        if (custBean.size()>0) return new ResponseEntity<List<CustomerBean>>(custBean, HttpStatus.OK);
        else
            //throw new CustomerNotFoundException(customerID);
            throw new RecordNotFoundException("Invalid Customer ID: " + customerID);

    }


    // @GetMapping(value = "api/v2/name/{customerName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.MyResponseViews.class)
    public ResponseEntity<List<CustomerBean>> getCustomerByName(@PathVariable("customerName") String customerName) {
        //this.customerName = customerName;
        List<CustomerBean> custBean = customerService.findAllCustomersBycustomerName(customerName);
        if (custBean.size() > 0) return new ResponseEntity<List<CustomerBean>>(custBean, HttpStatus.OK);
        else
            //return new ResponseEntity<List<CustomerBean>>(HttpStatus.NOT_FOUND);
            throw new RecordNotFoundException("Customer with Name " + customerName + " not found.");
    }

    @Operation(summary = "Get Customers in the Bill Amount Range",tags = {"CustomerBillingSystem"})
    @GetMapping(value = "api/v2/{BillAmount1}/{BillAmount2}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerBean>> getCustomerByBillAmount(@PathVariable("BillAmount1") double BillAmount1, @PathVariable("BillAmount2") double BillAmount2) {
        List<CustomerBean> custBean = customerService.findAllCustomersByBillAmountBetween(BillAmount1, BillAmount2);
        if (custBean.size() > 0) return new ResponseEntity<List<CustomerBean>>(custBean, HttpStatus.OK);
        else
            throw new RecordNotFoundException("Customer Bills not found between " + BillAmount1 + " to " + BillAmount2);
    }

    @Operation(summary = "Get Customers either by ID or Name or Full Customers list",tags = {"CustomerBillingSystem"})
    @GetMapping(value = "api/v2/customers",produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.MyResponseViews.class)
    public ResponseEntity<List<CustomerBean>> getCustomersApi(@RequestHeader(name = "Type",required = false) String type,@RequestParam(value = "id", required = false) Integer id,
                                                               @RequestParam(value = "name",required = false) String name){

       return customerService.getCustomerApi(id,name,type);

    }


    }
