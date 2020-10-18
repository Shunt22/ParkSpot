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
	private String timeStart;
	private String timeFinish;

	public ParkingLotEntity copy(ParkingLotDto dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.vertices = new ArrayList<>(dto.getVertices());
		this.timeStart = dto.getTimeStart();
		this.timeFinish = dto.getTimeFinish();
		return this;
	}

	public ParkingLotDto toParkingLotDto() {
		return new ParkingLotDto(id, new ArrayList<>(vertices), name, timeStart, timeFinish);
	}
}
