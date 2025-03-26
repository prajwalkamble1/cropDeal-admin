package com.admin.feign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.admin.model.Farmer;

@Component
public class FarmerFeignImp {

	@Autowired
	FarmerFeign fm;
	
	public ResponseEntity<List<Farmer>> getAll(){
		List<Farmer> list  = fm.getAll().getBody();
		return new ResponseEntity<List<Farmer>>(list, HttpStatus.OK);
	}
	public ResponseEntity<Farmer> getbyId(int id){
		return new ResponseEntity<Farmer>(fm.getById(id).getBody(),HttpStatus.OK);
	}
	
	public ResponseEntity<Farmer> deleteById(int id){
	return new ResponseEntity<Farmer>(fm.deleteById(id).getBody(), HttpStatus.OK);
	}

	
}
