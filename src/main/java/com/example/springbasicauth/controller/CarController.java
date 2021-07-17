package com.example.springbasicauth.controller;

import com.example.springbasicauth.model.Car;
import com.example.springbasicauth.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    public List<Car> getAllCars() {
        //TODO: APIResponse Class
        return carService.findAll();
    }

}
