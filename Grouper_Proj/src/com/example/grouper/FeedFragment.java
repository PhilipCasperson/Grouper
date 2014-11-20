package com.example.grouper;


import java.util.List;

import com.example.grouper.database.DatabaseHandler;
import com.example.grouper.database.Event;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class FeedFragment extends ListFragment {
	public static final String TAG = FeedFragment.class.getSimpleName();
	
	private DatabaseHandler dbHandler;
	private Context fContext;
	private List<Event> events;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.feedfragment, container, false);
		fContext = getActivity();
		dbHandler = new DatabaseHandler(fContext);
		events = dbHandler.getAllEvents();
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

