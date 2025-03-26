package com.admin.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.admin.model.Dealer;


@FeignClient("dealer")
public interface DealerFeign {

@GetMapping("/getAllDealer")
public ResponseEntity<List<Dealer>> getAll();

@GetMapping("/getById/{id}")
public ResponseEntity<Dealer> getById(@PathVariable int id);

@DeleteMapping("/delete/{id}")
public ResponseEntity<Dealer> deleteById(@PathVariable int id);

}
