package com.superstore.useraccount.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address1;
    private String city;
    private String country;

    public Address(String address1, String city, String country) {
        this.address1 = address1;
        this.city = city;
        this.country = country;
    }
}
