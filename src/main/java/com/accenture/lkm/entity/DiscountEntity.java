package com.accenture.lkm.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "discount")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


    private Long promoCode;
    private int discountAvailable;
    private LocalDate validity;
}
