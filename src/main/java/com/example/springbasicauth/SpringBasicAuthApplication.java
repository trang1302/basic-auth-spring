package com.example.springbasicauth;

import com.example.springbasicauth.model.Car;
import com.example.springbasicauth.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBasicAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicAuthApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CarRepository carRepository) {
		return args -> {
			//Initial cars to be added to DB for demonstration purposes
			List<Car> initCars = new ArrayList<>();
			initCars.add(new Car("BMW", "440i", 2016));
			initCars.add(new Car("VW", "GTI", 2016));
			initCars.add(new Car("Mercedes", "GLK", 2014));

			for (Car car : initCars) {
				carRepository.save(car);
			}
		};
	}

}
