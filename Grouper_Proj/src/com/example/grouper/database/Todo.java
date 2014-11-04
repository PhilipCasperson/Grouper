package com.example.grouper.database;

public class Todo {
	//private variables
	int _id;
	String _name;
	String _description;
	
	//empty constructor
	public Todo() {
		
	}
	public Todo (int id, String name, String description){
		this._id = id;
		this._name = name;
		this._description = description;
	}
	
	public Todo(String name, String description){
		this._name = name;
		this._description = description;
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
		public String toString(){
			return this._name;
		}

}
