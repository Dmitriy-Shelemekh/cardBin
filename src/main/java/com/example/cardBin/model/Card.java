package com.example.cardBin.model;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Card {
    @Size(min=40000000, max=50000000)
    private Integer bin;
    private Number number;
    private String scheme;
    private String type;
    private String brand;
    private Boolean prepaid;
    private Country country;
    private Bank bank;

    public Card(Number number, String scheme, String type, String brand, Boolean prepaid, Country country, Bank bank) {
        this.number = number;
        this.scheme = scheme;
        this.type = type;
        this.brand = brand;
        this.prepaid = prepaid;
        this.country = country;
        this.bank = bank;
    }
}
