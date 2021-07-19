package com.example.springbasicauth.controller;

import com.example.springbasicauth.model.APIResponse;
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
    public APIResponse<List<Car>> getAllCars() {
        APIResponse<List<Car>> apiResponse = new APIResponse<>();
        try {
            apiResponse.setData(carService.findAll());
        } catch (Exception e) {
            apiResponse.setError(e.getMessage());
        }
        return apiResponse;
    }

}
