package com.taxi.config;


public interface AdminConfigService {
	public String checkAdminCredentials(String oldusername, String oldpassword);
	public String updateAdminCredentialsService(String newusername, String newpassword, String oldusername);

}
