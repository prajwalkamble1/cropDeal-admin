package com.admin.feign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.admin.model.Dealer;

@Component
public class DealerFeignImp {
	
	@Autowired
	DealerFeign df;

	public ResponseEntity<List<Dealer>> getAll(){
		List<Dealer> list  = df.getAll().getBody();
		return new ResponseEntity<List<Dealer>>(list, HttpStatus.OK);
	}
	public ResponseEntity<Dealer> getbyId(int id){
		return new ResponseEntity<Dealer>(df.getById(id).getBody(),HttpStatus.OK);
	}

	public ResponseEntity<Dealer> deleteById(int id){
	return new ResponseEntity<Dealer>(df.deleteById(id).getBody(), HttpStatus.OK);
	}


}
