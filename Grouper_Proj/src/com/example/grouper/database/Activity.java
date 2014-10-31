package com.example.grouper.database;

public class Activity {
	private long id;
	private String activityName;
	private String activityDescription;
	
	public long getId(){
		return id;
	}
	public void setId(long id){
		this.id = id;
	}
	
	public String getActivityName(){
		return activityName;
	}
	public void setActivityName(String activityName){
		this.activityName = activityName;
	}
	
	public String activityDescription(){
		return activityDescription;
	}
	public void setActivityDescription(String activityDescription){
		this.activityDescription = activityDescription;
	}
}
