package com.example.springbasicauth.repository;

import com.example.springbasicauth.model.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findAll();

    Car findById(long id);
}
