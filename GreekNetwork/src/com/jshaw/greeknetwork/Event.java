package com.jshaw.greeknetwork;

public class Event {

	private String name;
	private long date;
	private String details;
	private int ID;
	
	public Event(String name, long date, String details, int iD) 
	{
		this.name = name;
		this.date = date;
		this.details = details;
		ID = iD;
	}
	
	public Event(String name, long date, String details) 
	{
		this.name = name;
		this.date = date;
		this.details = details;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public long getDate() 
	{
		return date;
	}
	
	public void setDate(long date) 
	{
		this.date = date;
	}
	
	public String getDetails() 
	{
		return details;
	}
	
	public void setDetails(String details) 
	{
		this.details = details;
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
