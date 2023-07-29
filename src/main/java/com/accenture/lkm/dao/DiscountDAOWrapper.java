package com.accenture.lkm.dao;


import com.accenture.lkm.entity.DiscountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountDAOWrapper{

    @Autowired
    private DiscountDAO discountDAO;


    public DiscountEntity getDiscountDetails(long promoCode) {

        return discountDAO.getDiscountDetails(promoCode);
    }
}
