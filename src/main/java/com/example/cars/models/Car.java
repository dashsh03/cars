package com.example.cars.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModelProperty;



@Entity
@Table(name = "cars")   // the table in the database that will contain our cars data
@EntityListeners(AuditingEntityListener.class)
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "car_Name", nullable = false)
	private String carName;
	
	@Column(name = "doors", nullable = false)
	private int doors;
	
	
	public Car() {
		
	}
	
	public Car(String carName, int doors) {
		
		this.carName = carName;
		System.out.println("Bean Car Name " +this.carName);
		
		this.doors = doors;
		System.out.println("Bean Doors " +this.doors);
		//this.email = email;
	}
	
	
	@ApiModelProperty(name="id",
            value="The id of the car",
            example="1")
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
	@ApiModelProperty(name="carName",
            value="The name of the car to be saved",
            example="Bugatti",
            required=true)
	
	public String getCarName() {
		
		System.out.println("Bean 2 Car name :" +carName);
		return carName;
	}
	
	public void setCarName(String carName) {
		this.carName = carName;
	}
	
	@ApiModelProperty(name="doors",
            value="The number of doors that the car has",
            example="2",
            required=true)
	
	public int getDoors() {
		
		System.out.println("Bean 2 doors :" +doors);
		return doors;
	}
	
	public void setDoors(int doors) {
		this.doors = doors;
	}
	

}
