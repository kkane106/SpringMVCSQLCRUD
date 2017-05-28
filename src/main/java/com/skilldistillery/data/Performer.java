package com.skilldistillery.data;

public class Performer {
	private int id;
	private String name;
	
	public Performer () {
		super();
	}
	
	public Performer(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
