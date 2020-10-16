package ru.shunt.parking.pojo;

import lombok.Value;

import java.util.List;

@Value
public class ParkingLot {

	List<Vertex> vertices;
	String name;


	@Value
	public static class Vertex{
		double x;
		double y;
	}

}
