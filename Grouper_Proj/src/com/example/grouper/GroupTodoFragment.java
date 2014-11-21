package com.example.grouper;

import java.util.List;

import com.example.grouper.database.DatabaseHandler;
import com.example.grouper.database.Todo;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class GroupTodoFragment extends ListFragment {
	public static final String TAG = GroupTodoFragment.class.getSimpleName();
	
	private DatabaseHandler dbHandler;
	private Context fContext;
	private List<Todo> todos;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		GroupViewActivity parentActivity = (GroupViewActivity)getActivity();
		View rootView = inflater.inflate(R.layout.todofragment, container, false);
		fContext = getActivity();
		dbHandler = new DatabaseHandler(fContext);
		todos = dbHandler.getTodosFromGroup(parentActivity._group.getID());
		ArrayAdapter<Todo> adapter = new ArrayAdapter<Todo>(fContext, android.R.layout.simple_list_item_1, todos);
		setListAdapter(adapter);
		
		for (Todo td : todos){
			String log = "Id: " + td.getID() + ", Name: " + td.getName() + " , Description" + td.getDescription();
			//Write events to log
			Log.d("Name: ", log);
		}
		return rootView;
	}
	
	
}
