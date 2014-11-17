package com.example.grouper;


import java.util.List;

import com.example.grouper.database.DatabaseHandler;
import com.example.grouper.database.Todo;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//public class ToDoFragment extends ListFragment {
//	String[] activities={"todo 1", "todo 1", "todo 2", "todo 3", "todo 4", "todo 5", "todo 6", "todo 7", "todo 8"};
//	//Call when todo is first created
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		
//		//Inflate the layout for this fragment
//		View rootView = inflater.inflate(R.layout.todofragment, container, false);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, activities);
//		setListAdapter(adapter);
//		return rootView;
//	}
//}

public class ToDoFragment extends ListFragment {
	public static final String TAG = ToDoFragment.class.getSimpleName();
	
	private DatabaseHandler dbHandler;
	private Context fContext;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.todofragment, container, false);
		fContext = getActivity();
		dbHandler = new DatabaseHandler(fContext);
		List<Todo> todoList = dbHandler.getAlltodos();
		ArrayAdapter<Todo> adapter = new ArrayAdapter<Todo>(fContext, android.R.layout.simple_list_item_1, todoList);
		setListAdapter(adapter);
		
		for (Todo to : todoList){
			String log = "Id: " + to.getID() + ", Name: " + to.getName() + " , Description" + to.getDescription();
			//Write events to log
			Log.d("Name: ", log);
		}
		return rootView;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		//Intent intent = new Intent(getActivity().getApplicationContext(), NewActivity.class);
		//startActivity(intent);
	}
	
}