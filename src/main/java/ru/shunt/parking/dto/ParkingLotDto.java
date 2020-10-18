package ru.shunt.parking.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@RequiredArgsConstructor
public class ParkingLotDto {

	long id;
	List<Vertex> vertices;
	String name;
	String timeStart;
	String timeFinish;

	public ParkingLotDto() {
		id = 0;
		vertices = new ArrayList<>();
		name = "";
		timeStart = "";
		timeFinish = "";
	}


	@Value
	public static class Vertex {
		double x;
		double y;
	}

}
