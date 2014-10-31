package com.example.grouper.database;

public class todo {
	private long id;
	private String todoTitle;
	private String todoComments;
	
	public long getId(){
		return id;
	}
	public void setId(long id){
		this.id = id;
	}
	
	public String getTodoTitle(){
		return todoTitle;
	}
	public void setTodoTitle(String todoTitle){
		this.todoTitle = todoTitle;
	}
	
	public String getTodoComments(){
		return todoComments;
	}
	public void setTodoComments(String todoComments){
		this.todoComments = todoComments;
	}

}
