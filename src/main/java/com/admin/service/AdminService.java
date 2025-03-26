package com.admin.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dto.AdminDTO;
import com.admin.exception.InvalidAdminException;
import com.admin.model.Admin;
import com.admin.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin findById(int id) throws InvalidAdminException {
    	return adminRepository.findById(id)
                .orElseThrow(() -> {
                    return new InvalidAdminException("Invalid Id");
                });
    }

    public Admin addAdmin(AdminDTO s) {
    		Admin a = new Admin();
        	a.setName(s.getName());
        	a.setPassword(s.getPassword());
        	a.setUserName(s.getUserName());
        	a.setEmail(s.getEmail());
            return adminRepository.save(a);
    }

    public Admin deleteById(int id) {
    	try {
    		adminRepository.deleteById(id);
    	}
    	catch(InvalidAdminException e){
    		throw new InvalidAdminException("Invalid Id");
    	}
		return null;
        
    }
    
	public Admin authAdmin(String userName) throws InvalidAdminException {
		Optional<Admin> admin = adminRepository.findByUserName(userName);
		if(admin.isPresent()) {
			return admin.get();
		}else {
			throw new InvalidAdminException("No admin found");
		}
    }
}
