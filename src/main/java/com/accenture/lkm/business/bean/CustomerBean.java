package com.accenture.lkm.business.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.accenture.lkm.views.Views;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerBean {

    @JsonView(Views.MyResponseViews.class)
    private int customerId;
    @JsonView(Views.MyResponseViews.class)
    @NotNull(message = "Customer Name is mandatory")
    private String customerName;

    @NotNull(message = "Bill Amount is mandatory")
    private Double billAmount;

    private long promoCode;
    @JsonView(Views.MyResponseViews.class)
    private double finalPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",timezone = "IST")
    @JsonView(Views.MyResponseViews.class)
    private LocalDate billDate;


}
	
