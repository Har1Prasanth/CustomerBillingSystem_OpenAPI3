package com.accenture.lkm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="customer_bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "customerid")
	private int customerId;
	@Column(name = "customername")
	private String customerName;
	@Column(name = "billamount")
	private Double billAmount;
	@Column(name = "finalprice")
	private Double finalPrice;

	@Column(name = "billdate")
	private LocalDate billDate;

}
