package com.accenture.lkm.business.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.accenture.lkm.custom.validation.BillDateValidator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerBean{
	
	private int customerId;
	//@NotNull(message = "Customer Name is a mandatory field")
	private String customerName;

	//@Range(min=1000, max=100000,message = "Range.customerBean.billAmount")
	private Double billAmount;
	//@BillDateValidator(message = "{BilldateValidator.customerBean.billDate}")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "UTC")
	private Date billDate;


}
	
