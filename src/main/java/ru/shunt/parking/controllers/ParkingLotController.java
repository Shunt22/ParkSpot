package ru.shunt.parking.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.shunt.parking.database.ParkingLotEntity;
import ru.shunt.parking.database.ParkingLotEntityRepository;
import ru.shunt.parking.dto.ParkingLotDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ParkingLotController {


	@Autowired
	ParkingLotEntityRepository parkingLotEntityRepository;

	@GetMapping(path = "/returnAll")
	public List<ParkingLotDto> returnAll() {
		List<ParkingLotEntity> all = (List<ParkingLotEntity>) parkingLotEntityRepository.findAll();
		return  all.stream().map(ParkingLotEntity::toParkingLotDto).collect(Collectors.toList());

	}

	@PostMapping("/addNew")
	public void addNewParkingLot(@RequestBody ParkingLotDto p) {
		//log.debug(p.toString());
		parkingLotEntityRepository.save(new ParkingLotEntity().copy(p));
	}

}
