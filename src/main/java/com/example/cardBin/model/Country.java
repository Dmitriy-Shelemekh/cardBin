package com.example.cardBin.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Country {
    private Integer numeric;
    private String alpha2;
    private String name;
    private String emoji;
    private String currency;
    private Integer latitude;
    private Integer longitude;
}
