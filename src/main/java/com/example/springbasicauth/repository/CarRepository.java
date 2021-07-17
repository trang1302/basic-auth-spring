package com.example.springbasicauth.repository;

import com.example.springbasicauth.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {


}
