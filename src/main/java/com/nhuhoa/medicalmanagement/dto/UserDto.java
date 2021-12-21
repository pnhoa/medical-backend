package com.nhuhoa.medicalmanagement.dto;

public class UserDto extends AbstractDto {
	
	private String userName;

	private String password;

	private String fullName;

	private String phone;

	private String email;

	private int enabled;
	
	private String roleCode;
	

	public UserDto(String userName, String password, String fullName, String phone, String email, int enabled) {
		super();
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.enabled = enabled;
	}

	public UserDto() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}


	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	

}
