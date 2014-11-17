package com.example.grouper;


import java.util.List;

import com.example.grouper.database.DatabaseHandler;
import com.example.grouper.database.Event;

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

//public class DiscoverFragment extends ListFragment {
//	String[] activities={"Activity 1", "Activity 1", "Activity 2", "Activity 3", "Activity 4", "Activity 5", "Activity 6", "Activity 7", "Activity 8", "Activity 9", "Activity 10"};
//	//Call when activity is first created
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		
//		//Inflate the layout for this fragment
//		View rootView = inflater.inflate(R.layout.discoverfragment, container, false);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, activities);
//		setListAdapter(adapter);
//		return rootView;
//	}
//}

public class DiscoverFragment extends ListFragment {
	public static final String TAG = DiscoverFragment.class.getSimpleName();
	
	private DatabaseHandler dbHandler;
	private Context fContext;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.discoverfragment, container, false);
		fContext = getActivity();
		dbHandler = new DatabaseHandler(fContext);
		List<Event> events = dbHandler.getAllEvents();
		ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(fContext, android.R.layout.simple_list_item_1, events);
		setListAdapter(adapter);
		
		for (Event ev : events){
			String log = "Id: " + ev.getID() + ", Name: " + ev.getName() + " , Description" + ev.getDescription();
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
