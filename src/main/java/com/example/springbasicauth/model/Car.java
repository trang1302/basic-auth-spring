package com.example.springbasicauth.model;

import lombok.Data;

@Data
public class Car {
    private final Integer id;
    private final String make;
    private final String model;
    private final Integer year;
}
