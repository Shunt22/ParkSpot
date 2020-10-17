package ru.shunt.parking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shunt.parking.database.ParkingLotEntityRepository;
import ru.shunt.parking.database.ParkingLotEntity;
import ru.shunt.parking.dto.ParkingLotDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ParkingLotController {


	@Autowired
	ParkingLotEntityRepository parkingLotEntityRepository;

	@RequestMapping(path = "/returnAll")
	public List<ParkingLotDto> returnAll() {
//		ru.shunt.parking.pojo.ParkingLot.Vertex vertex1 = new ru.shunt.parking.pojo.ParkingLot.Vertex(55.75,  37.80);
//		ru.shunt.parking.pojo.ParkingLot.Vertex vertex2 = new ru.shunt.parking.pojo.ParkingLot.Vertex(53.50, 37.80);
//		ru.shunt.parking.pojo.ParkingLot.Vertex vertex3 = new ru.shunt.parking.pojo.ParkingLot.Vertex(53.50, 38.00);
//		ru.shunt.parking.pojo.ParkingLot.Vertex vertex4 = new ru.shunt.parking.pojo.ParkingLot.Vertex(55.75, 38.00);
//		ru.shunt.parking.pojo.ParkingLot p = new ru.shunt.parking.pojo.ParkingLot(1,
//				Arrays.asList(vertex1,vertex2,vertex3,vertex4),
//				"Test name");
//	return Arrays.asList(p);
		List<ParkingLotEntity> all = (List<ParkingLotEntity>) parkingLotEntityRepository.findAll();
		return  all.stream().map(ParkingLotEntity::toParkingLot).collect(Collectors.toList());

	}

	@PostMapping("/addNew")
	public void addNewParkingLot(@RequestBody ParkingLotDto p) {

		parkingLotEntityRepository.save(new ParkingLotEntity().copy(p));
	}

}
