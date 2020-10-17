package ru.shunt.parking.database;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shunt.parking.pojo.ParkingLot;
import ru.shunt.parking.utility.VertexListConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ParkingLots")
public class ParkingLotTable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Convert(converter = VertexListConverter.class)
	private List<ParkingLot.Vertex> vertices;
	private String name;

	public ParkingLotTable copy(ParkingLot p) {
		this.id = p.getId();
		this.name = p.getName();
		this.vertices = new ArrayList<>(p.getVertices());
		return this;
	}

	public ParkingLot toParkingLot() {
		return new ParkingLot(id, new ArrayList<>(vertices), name);
	}
}
