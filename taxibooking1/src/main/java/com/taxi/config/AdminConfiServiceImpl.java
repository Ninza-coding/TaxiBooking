package com.taxi.config;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taxi.dao.AdminDao;
import com.taxi.model.Admin;



@Service
public class AdminConfiServiceImpl implements AdminConfigService {
	private AdminDao adminDao;
	@Autowired
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	private PasswordEncoder passwordEncoder;
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String checkAdminCredentials(String oldusername, String oldpassword) {
		Optional<Admin> byUsername = adminDao.findByUsername(oldusername);
		if(byUsername.isPresent()) {
			Admin admin = byUsername.get();
			boolean matches = passwordEncoder.matches(oldpassword, admin.getPassword());
			if(matches) {
				return "SUCCESS";
			}else {
				return "Wrong Credentials";
			}
		}else {
			return"Wrong Credentials";
		}
	}

	@Override
	public String updateAdminCredentialsService(String newusername, String newpassword, String oldusername) {
		
		int upadteCredentialsDao = adminDao.upadteCredentialsDao(newusername, passwordEncoder.encode(newpassword), oldusername);
		if(upadteCredentialsDao==1) {
			return "Password Successfully Updates";
		}else {
			return "Failed To Update";
		}
		
	}

}
