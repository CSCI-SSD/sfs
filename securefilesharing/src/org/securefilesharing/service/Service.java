package org.securefilesharing.service;

import org.securefilesharing.beans.DoumentList;
import org.securefilesharing.beans.LoginBean;
import org.securefilesharing.beans.SignupBean;

public interface Service {
	
	public boolean findUserByEmail(String email);
	
	public String createUser(SignupBean signupBean);
	
	public String updateUser(SignupBean signupBean);
	
	public LoginBean validateUserLogin(LoginBean loginBean);

	public String uploadFile(String fileName, String email);
	
	public DoumentList getFileList(String email);
}
