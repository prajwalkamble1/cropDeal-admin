package com.admin.service;

import com.admin.dto.AdminDTO;
import com.admin.exception.InvalidAdminException;
import com.admin.model.Admin;

public interface IAdminService {
	Admin addAdmin(AdminDTO adminDTO);
	Admin findById(long id) throws InvalidAdminException;
	Admin deleteAdmin(long id) throws InvalidAdminException;
	Admin authAdmin(String userName) throws InvalidAdminException;
}
