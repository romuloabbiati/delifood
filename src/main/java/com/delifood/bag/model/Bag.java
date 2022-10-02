package com.delifood.bag.model;

import com.delifood.bag.enumeration.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Bag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;

    private Double totalPrice;

    @Enumerated
    private PaymentType paymentType;

    private Boolean closed;

}
