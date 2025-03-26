package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.AdminDTO;
import com.admin.exception.AdminNotFoundException;
import com.admin.model.Admin;
import com.admin.service.AdminService;

@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    

    @GetMapping("/{Id}")
     public ResponseEntity<Admin> findById(@PathVariable int Id) {
        try {
            return new ResponseEntity<>(adminService.findById(Id), HttpStatus.OK);
        	
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
     public ResponseEntity<Admin> addAdmin(@RequestBody AdminDTO s) throws AdminNotFoundException {
            return new ResponseEntity<>(adminService.addAdmin(s), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable int Id){
    	adminService.deleteById(Id);
    	return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
    }
    
    @GetMapping("/auth/{userName}")
    public ResponseEntity<Admin> authAdmin (@PathVariable String userName){
    	return new ResponseEntity<Admin>(adminService.authAdmin(userName), HttpStatus.OK);
    }
}