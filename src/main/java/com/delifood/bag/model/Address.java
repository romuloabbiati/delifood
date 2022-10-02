package com.delifood.bag.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@AllArgsConstructor
@Builder
@Data
@Embeddable
@NoArgsConstructor
public class Address {

    private String postCode;

    private String place;

}
