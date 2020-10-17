package ru.shunt.parking.database;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shunt.parking.dto.ParkingLotDto;
import ru.shunt.parking.utility.VertexListConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ParkingLots")
public class ParkingLotEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Convert(converter = VertexListConverter.class)
	private List<ParkingLotDto.Vertex> vertices;
	private String name;

	public ParkingLotEntity copy(ParkingLotDto p) {
		this.id = p.getId();
		this.name = p.getName();
		this.vertices = new ArrayList<>(p.getVertices());
		return this;
	}

	public ParkingLotDto toParkingLot() {
		return new ParkingLotDto(id, new ArrayList<>(vertices), name);
	}
}
