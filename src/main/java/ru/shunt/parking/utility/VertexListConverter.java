package ru.shunt.parking.utility;

import ru.shunt.parking.dto.ParkingLotDto;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class VertexListConverter implements AttributeConverter<List<ParkingLotDto.Vertex>, String> {
	@Override
	public String convertToDatabaseColumn(List<ParkingLotDto.Vertex> attribute) {
		if(attribute == null || attribute.isEmpty()){
			return "";
		}
		return attribute.stream()
				.map(v -> String.format("%f %f", v.getX(), v.getY()))
				.collect(Collectors.joining("|"));
	}

	@Override
	public List<ParkingLotDto.Vertex> convertToEntityAttribute(String dbData) {
		if(dbData==null || dbData.isEmpty()){
			return new ArrayList<>();
		}
		String[] vertices = dbData.split("\\|");
		List<ParkingLotDto.Vertex> resultList = new ArrayList<>();
		for (String vertex: vertices){
			String[] s1 = vertex.split(" ");
			resultList.add(new ParkingLotDto.Vertex(Double.parseDouble(s1[0]), Double.parseDouble(s1[1])));
		}
		return  resultList;
	}
}
