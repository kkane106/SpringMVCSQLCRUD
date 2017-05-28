package com.skilldistillery.data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	@NotNull
	private String fname;
	@NotNull
	private String lname;
	@Size(min=3, max=35)
	private String username;
	@Size(min=3, max=35)
	private String password;
	
	
	public User(){super();}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String email) {
		this.username = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User(int id, String fname, String lname, String userName, String password) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.username = userName;
		this.password = password;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}

	public User(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [fname=" + fname + ", lname=" + lname + ", username=" + username + ", password=" + password
				+ ", id=" + id + "]";
	}

}
