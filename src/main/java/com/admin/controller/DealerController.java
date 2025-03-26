package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.admin.feign.DealerFeign;
import com.admin.model.Dealer;

import jakarta.validation.Valid;


@RestController
public class DealerController {
	
	@Autowired
	DealerFeign dealerFeign;
	
	@GetMapping("/getAll/dealer")
	public ResponseEntity<List<Dealer>> getAll(){
		return dealerFeign.getAll();
	}
	
	@GetMapping("/viewDealerById/{DealerId}")
		public ResponseEntity<Dealer> viewDealerById (@PathVariable int dealerId){
		return dealerFeign.getById(dealerId);
	}
	
	@DeleteMapping("/deleteFarmer/{DealerId}")
	public ResponseEntity<Dealer> deleteDealer(@Valid @PathVariable int dealerId){
		return dealerFeign.deleteById(dealerId);
	}

}