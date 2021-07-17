package com.example.springbasicauth.service;

import com.example.springbasicauth.model.Car;
import com.example.springbasicauth.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }
}
