package com.skilldistillery.data;

import java.util.ArrayList;
import java.util.List;

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
	
	private int id;
	private List<Concert> concertList;
	
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
	
	
	
	public List<Concert> getConcertList() {
		return concertList;
	}
	public void setConcertList(List<Concert> concertList) {
		this.concertList = concertList;
	}
	
	public void addConcert(Concert c){
		if(concertList==null){
			concertList = new ArrayList<Concert>();
			concertList.add(c);
		}
		else{
			concertList.add(c);
		}
	}
	
	public void removeConcert(Concert c){
		concertList.remove(c);
	}
	
	@Override
	public String toString() {
		return "User [fname=" + fname + ", lname=" + lname + ", username=" + username + ", password=" + password
				+ ", id=" + id + "]";
	}

}
