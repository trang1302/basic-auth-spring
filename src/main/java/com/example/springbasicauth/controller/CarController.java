package com.example.springbasicauth.controller;

import com.example.springbasicauth.model.APIResponse;
import com.example.springbasicauth.model.Car;
import com.example.springbasicauth.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CarController {

    @Autowired
    CarService carService;

    @PreAuthorize("hasAnyRole('ROLE_BUYER', 'ROLE_SELLER')")
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

    @PreAuthorize("hasAnyRole('ROLE_SELLER')")
    @PostMapping
    public APIResponse<Car> getAllCars(@RequestBody Car car) {
        APIResponse<Car> apiResponse = new APIResponse<>();
        try {
            carService.sellCar(car);
            apiResponse.setData(carService.findById(car.getId()));
        } catch (Exception e) {
            apiResponse.setError(e.getMessage());
        }
        return apiResponse;
    }


}
