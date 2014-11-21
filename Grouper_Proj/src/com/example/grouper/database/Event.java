package com.example.grouper.database;

public class Event {
	//private var
	int _id;
	String _name;
	String _description;
	int _groupId;
	
	//Empty constructor
	public Event(){
		
	}
	//constructor
	public Event (int id, String name, String description, int groupid){
		this._id = id;
		this._name = name;
		this._description = description;
		this._groupId = groupid;
	}
	//another constructor
	public Event(String name, String description){
		this._name = name;
		this._description = description;
	}
	public Event(String name, String description, int groupid){
		this._name = name;
		this._description = description;
		this._groupId = groupid;
	}
	//get ID
	public int getID(){
		return this._id;
	}
	//set ID
	public void setID(int id){
		this._id = id;
	}
	//get name
	public String getName(){
		return this._name;
	}
	//set name
	public void setName(String name){
		this._name = name;
	}
	//get description
	public String getDescription(){
		return this._description;
	}
	//set description
	public void setDescription(String description){
		this._description = description;
	}
	//get ID
	public int getGroupId(){
		return this._groupId;
	}
	//set ID
	public void setGroupId(int id){
		this._groupId = id;
	}
	@Override
	public String toString(){
		return this._name;
	}
}
