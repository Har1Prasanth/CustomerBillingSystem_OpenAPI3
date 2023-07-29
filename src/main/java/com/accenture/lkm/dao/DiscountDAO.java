package com.accenture.lkm.dao;

import com.accenture.lkm.entity.DiscountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DiscountDAO extends CrudRepository<DiscountEntity, Long> {

    @Query(value = "SELECT u FROM DiscountEntity u WHERE u.promoCode = :promoCode")
    DiscountEntity getDiscountDetails(long promoCode);
}
