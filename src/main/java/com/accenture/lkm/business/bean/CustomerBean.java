package com.accenture.lkm.business.bean;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.accenture.lkm.custom.validation.BillDateValidator;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerBean{
	
	private int customerId;
	private String customerName;
	@Range(min=1000, max=100000,message = "Range.customerBean.billAmount")
	private Double billAmount;
	@BillDateValidator(message = "{BilldateValidator.customerBean.billDate}")
	private Date billDate;
}
	