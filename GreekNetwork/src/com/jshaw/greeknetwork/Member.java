package com.jshaw.greeknetwork;

public class Member {

	private String name;
	private String year;
	private String position;
	private String comments;
	private String number;
	private int ID;
	
	public Member(String name, String year, String position, String comments, String number, int iD) 
	{
		this.name = name;
		this.year = year;
		this.position = position;
		this.comments = comments;
		this.number = number;
		ID = iD;
	}
	
	public Member(String name, String year, String position, String comments, String number) 
	{
		this.name = name;
		this.year = year;
		this.position = position;
		this.comments = comments;
		this.number = number;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getYear() 
	{
		return year;
	}
	
	public void setYear(String year) 
	{
		this.year = year;
	}
	
	public String getPosition() 
	{
		return position;
	}
	
	public void setPosition(String position) 
	{
		this.position = position;
	}
	
	public String getComments() 
	{
		return comments;
	}	
	
	public void setComments(String comments) 
	{
		this.comments = comments;
	}
	
	public String getNumber() 
	{
		return number;
	}
	
	public void setNumber(String number) 
	{
		this.number = number;
	}
	
	public int getID() 
	{
		return ID;
	}
	
	public void setID(int iD) 
	{
		ID = iD;
	}

	
}
