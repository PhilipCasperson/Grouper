package com.example.grouper;


import java.util.List;
import java.util.Random;

import com.example.grouper.database.Activity;
import com.example.grouper.database.ActivityDataSource;

import android.app.ListActivity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

//public class FeedFragment extends ListFragment {
//		
//	String[] activities={"Activity 1", "Activity 1", "Activity 2", "Activity 3", "Activity 4", "Activity 5", "Activity 6", "Activity 7", "Activity 8"};
//	//Call when activity is first created
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		//Inflate the layout for this fragment
//		View rootView = inflater.inflate(R.layout.feedfragment, container, false);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, activities);
//		setListAdapter(adapter);
//		return rootView;
//	}
//}
public class FeedFragment extends ListActivity {
	private ActivityDataSource datasource;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.feedfragment);
		Activity activity = null;
		
		datasource = new ActivityDataSource(this);
		datasource.open();
		//create activites
		//fill database with dummy data
		for(int i = 0; i < 7; i++){
		String[] activityName = new String[] {"New Document", "New Group Member", "Activity 3", 
				"Assignment Due Soon", "Checkpoint Reached", "Dummy Data", "Some more group things"};
		String[] activityDescription = new String[] {"Description", "Description", "Description", "Description", 
				"Description", "Description", "Description"};
		int nextInt = new Random().nextInt(7);
		activity = datasource.createActivity(activityName[nextInt], activityDescription[nextInt]);
		}
		List<Activity> values = datasource.getAllActivites();
		//Put elements in listview
		ArrayAdapter<Activity> adapter = new ArrayAdapter<Activity>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}
	
	
}
