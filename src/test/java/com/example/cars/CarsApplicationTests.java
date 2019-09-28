package com.example.cars;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.example.cars.CarsApplication;
import com.example.cars.models.Car;
//import com.tutorial.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarsApplicationTests {
	
	@Autowired
	  private TestRestTemplate restTemplate;
	
	@LocalServerPort
	  private int port;
	
	private String getRootUrl() {
		System.out.println("port is " + port);
	    return "http://localhost:"+port;
	  }
	
	@Test
	  public void contextLoads() throws Exception{
		}
	
	
	@Test
	  public void testCreateCar() {
	    Car car = new Car("Prius" , 4);
	 //   car.setCarName("Prius");
	 //   car.setDoors(4);

	    ResponseEntity<Car> postResponse = restTemplate.postForEntity(getRootUrl() + "/cars", car, Car.class);
	    Assert.assertNotNull(postResponse);
	    Assert.assertNotNull(postResponse.getBody());
	  }
	
	@Test
	  public void testUpdateCar() {
	    int id = 1;
	    Car car = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
	    car.setCarName("Tesla");
	    car.setDoors(2);

	    restTemplate.put(getRootUrl() + "/cars/" + id, car);

	    Car updatedCar = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
	    Assert.assertNotNull(updatedCar);
	  }
	
	
	@Test
	  public void testGetAllCars() {
	    HttpHeaders headers = new HttpHeaders();
	    HttpEntity<String> entity = new HttpEntity<String>(null, headers);

	    ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/cars",
	        HttpMethod.GET, entity, String.class);

	    Assert.assertNotNull(response.getBody());
	  }
	
	@Test
	  public void testGetCarById() {
	    Car car = restTemplate.getForObject(getRootUrl() + "/cars/1", Car.class);
			System.out.println("car name " +car.getCarName());
	    Assert.assertNotNull(car);
	  }
	
	
	
	@Test
	  public void testDeleteCar() {
	    int id = 2;
	    Car car = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
	    Assert.assertNotNull(car);

	    restTemplate.delete(getRootUrl() + "/cars/" + id);

	    try {
	      car = restTemplate.getForObject(getRootUrl() + "/cars/" + id, Car.class);
	    } catch (final HttpClientErrorException e) {
	      Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
	    }
	  }
	
	

}


