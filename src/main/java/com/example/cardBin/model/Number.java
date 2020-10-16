package com.example.cardBin.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Number {
    private int length;
    private boolean luhn;
}
