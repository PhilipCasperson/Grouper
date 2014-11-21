package com.example.grouper.database;

import java.util.List;

public class Group {
	//field vars
	int _id;
	String _name;
	String _description;
	
	//constructors
	public Group(){
		//empty
	}
	public Group(String name, String desc){
		//basic group
		this._name = name;
		this._description = desc;
	}
	public Group(int id, String name, String desc){
		//full group with id
		this._id = id;
		this._name = name;
		this._description = desc;
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
	//toString override
	public String toString(){
		return this._name;
	}

}
