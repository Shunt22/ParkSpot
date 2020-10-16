package ru.shunt.parking.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ParkingLot {

	@RequestMapping(path="/returnAll")
	public List<ru.shunt.parking.pojo.ParkingLot> returnAll(){
		ru.shunt.parking.pojo.ParkingLot.Vertex vertex1 = new ru.shunt.parking.pojo.ParkingLot.Vertex(20, 40);
		ru.shunt.parking.pojo.ParkingLot.Vertex vertex2 = new ru.shunt.parking.pojo.ParkingLot.Vertex(20, 50);
		ru.shunt.parking.pojo.ParkingLot.Vertex vertex3 = new ru.shunt.parking.pojo.ParkingLot.Vertex(40, 50);
		ru.shunt.parking.pojo.ParkingLot.Vertex vertex4 = new ru.shunt.parking.pojo.ParkingLot.Vertex(40, 40);
		ru.shunt.parking.pojo.ParkingLot p = new ru.shunt.parking.pojo.ParkingLot(Arrays.asList(vertex1,vertex2,vertex3,vertex4),"Test name");
		return Arrays.asList(p,p,p);
	}

	@PostMapping("/addNew")
	public void addNewParkingLot(@RequestBody ru.shunt.parking.pojo.ParkingLot p){
	}

}
