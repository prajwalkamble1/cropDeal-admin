package com.admin.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.admin.model.Farmer;

@FeignClient("farmer")
public interface FarmerFeign {

@GetMapping("/getall")
public ResponseEntity<List<Farmer>> getAll();

@GetMapping("/get/{id}")
public ResponseEntity<Farmer> getById(@PathVariable int id);

@DeleteMapping("/delete/{id}")
public ResponseEntity<Farmer> deleteById(@PathVariable int id);

}
