package ru.shunt.parking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shunt.parking.database.ParkingLotTableRepository;
import ru.shunt.parking.database.ParkingLotTable;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ParkingLotController {


	@Autowired
	ParkingLotTableRepository parkingLotTableRepository;

	@RequestMapping(path = "/returnAll")
	public List<ru.shunt.parking.pojo.ParkingLot> returnAll() {
//		ru.shunt.parking.pojo.ParkingLot.Vertex vertex1 = new ru.shunt.parking.pojo.ParkingLot.Vertex(55.75,  37.80);
//		ru.shunt.parking.pojo.ParkingLot.Vertex vertex2 = new ru.shunt.parking.pojo.ParkingLot.Vertex(53.50, 37.80);
//		ru.shunt.parking.pojo.ParkingLot.Vertex vertex3 = new ru.shunt.parking.pojo.ParkingLot.Vertex(53.50, 38.00);
//		ru.shunt.parking.pojo.ParkingLot.Vertex vertex4 = new ru.shunt.parking.pojo.ParkingLot.Vertex(55.75, 38.00);
//		ru.shunt.parking.pojo.ParkingLot p = new ru.shunt.parking.pojo.ParkingLot(1,
//				Arrays.asList(vertex1,vertex2,vertex3,vertex4),
//				"Test name");
//	return Arrays.asList(p);
		List<ParkingLotTable> all = (List<ParkingLotTable>) parkingLotTableRepository.findAll();
		return  all.stream().map(ParkingLotTable::toParkingLot).collect(Collectors.toList());

	}

	@PostMapping("/addNew")
	public void addNewParkingLot(@RequestBody ru.shunt.parking.pojo.ParkingLot p) {

		parkingLotTableRepository.save(new ParkingLotTable().copy(p));
	}

}
