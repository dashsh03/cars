package com.example.cars.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cars.exception.ResourceNotFoundException;
import com.example.cars.models.Car;
import com.example.cars.repository.CarRepository;
//import com.tutorial.model.User;

import io.swagger.annotations.ApiOperation;

@RestController
public class CarController {
	
	
	@Autowired
	private CarRepository carRepository;
	
	
	@ApiOperation(value = "Fetches all cars in the database.", response = Car.class)
	@GetMapping("/cars")   // GET Method for reading operation
	public List<Car> getAllCars() {
	    return carRepository.findAll();
	  }

	
	@ApiOperation(value = "Fetches a single car by its id.", response = Car.class)
	@GetMapping("/cars/{id}")    // GET Method for Read operation
	public ResponseEntity<Car> getCarsById(@PathVariable(value = "id") Long carId)
	      throws ResourceNotFoundException {

	    Car car = carRepository.findById(carId)
	                .orElseThrow(() -> new ResourceNotFoundException("Car " + carId + " not found"));
	    return ResponseEntity.ok().body(car);
	  }
	
	
	// POST Method for Create operation
    @PostMapping("/cars")
    public Car createCar(@Valid @RequestBody Car car) {
        return carRepository.save(car);
    }
	
	@ApiOperation(value = "Handles the editing of a single car's details.", response = Car.class)
	@PutMapping("/cars/{id}")    // PUT Method for Update operation
	  public ResponseEntity<Car> updateCar(
	      @PathVariable(value = "id") Long carId, @Valid @RequestBody Car carDetails)
	      throws ResourceNotFoundException {

	    Car car = carRepository
	                .findById(carId)
	                .orElseThrow(() -> new ResourceNotFoundException("Car " + carId + " not found"));

	    car.setCarName(carDetails.getCarName());
	    car.setDoors(carDetails.getDoors());

	    final Car updatedCar = carRepository.save(car);
	    return ResponseEntity.ok(updatedCar);
	  }
	
	
	@ApiOperation(value = "Handles the deletion of a single car by its id.", response = Car.class)
	  @DeleteMapping("/car/{id}")    // DELETE Method for Delete operation
	  public Map<String, Boolean> deleteCar(@PathVariable(value = "id") Long carId) throws Exception {
	    Car car = carRepository
	                .findById(carId)
	                .orElseThrow(() -> new ResourceNotFoundException("Car " + carId + " not found"));

	    carRepository.delete(car);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	  }
	
	
}



