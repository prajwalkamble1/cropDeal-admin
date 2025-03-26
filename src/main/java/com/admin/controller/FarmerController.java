package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.admin.feign.FarmerFeign;
import com.admin.model.Farmer;

import jakarta.validation.Valid;


@RestController
public class FarmerController {
	
@Autowired
FarmerFeign farmerFeign;
	
	@GetMapping("/getAll/farmer")
	public ResponseEntity  <List<Farmer>> getAll(){
		return farmerFeign.getAll();
	}
	
	@GetMapping("/viewFarmerById/{FarmerId}")
		public ResponseEntity<Farmer> viewFarmerById (@PathVariable int farmerId){
		return farmerFeign.getById(farmerId);
	}
	
	@DeleteMapping("/deleteFarmer/{FarmerId}")
	public ResponseEntity<Farmer> deleteStudent(@Valid @PathVariable int farmerId){
		return farmerFeign.deleteById(farmerId);
	}

}