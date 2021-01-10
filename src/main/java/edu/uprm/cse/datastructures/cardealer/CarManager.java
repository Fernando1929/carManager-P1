package edu.uprm.cse.datastructures.cardealer;


import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.model.CarList;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;

@Path("/cars")
public class CarManager {

	private final CircularSortedDoublyLinkedList<Car> cList = CarList.getInstance();
	
	//returns an array of cars 
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Car[] getAllCars() {
		Car[] result = new Car[cList.size()];
		for(int i =0; i< cList.size();i++) {
			result[i]=cList.get(i);
		}
		return result;
	}

	//returns the car with the specific id or throws Not found exception
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car get(@PathParam("id")long id) {
		for(Car i: cList) {
			if(i.getCarId()==id) {
				return i;
			}
		}
		throw new WebApplicationException(404);
	}

	//Adds a new car verifying that there isn't a car with the same id 
	//returns a created response or Bad request if there is a car with the same id
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCar(Car car) {
		for(Car i: cList) {
			if(i.getCarId()==car.getCarId()) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		}
		cList.add(car);
		return Response.status(Response.Status.CREATED).build();
	}

	//removes the specified car and add the updated version of the car
	//returns OK or Not found response
	@PUT
	@Path("/{id}/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCar(Car car) {
		for(Car i: cList) {
			if(i.getCarId()==car.getCarId()) {
				cList.remove(i);
				cList.add(car);
				return Response.status(200).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	//removes the car with the specified id
	//returns OK or Not found response
	@DELETE
	@Path("/{id}/delete")
	public Response deleteCar(@PathParam("id") long id) {
		for(Car i: cList) {
			if(i.getCarId()==id) {
				cList.remove(i);
				return Response.status(200).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
