package com.example.grouper;

import java.util.List;

import com.example.grouper.database.DatabaseHandler;
import com.example.grouper.database.Event;
import com.example.grouper.database.Group;

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

public class GroupFragment extends ListFragment {
	public static final String TAG = GroupFragment.class.getSimpleName();
	
	private DatabaseHandler dbHandler;
	private Context fContext;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.activity_group_fragment, container, false);
		fContext = getActivity();
		dbHandler = new DatabaseHandler(fContext);
		List<Group> groups = dbHandler.getAllgroups();
		ArrayAdapter<Group> adapter = new ArrayAdapter<Group>(fContext, android.R.layout.simple_list_item_1, groups);
		setListAdapter(adapter);
		
		for (Group gr : groups){
			String log = "Id: " + gr.getID() + ", Name: " + gr.getName() + " , Description" + gr.getDescription();
			//Write events to log
			Log.d("Name: ", log);
		}
		return rootView;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Group group = (Group)getListAdapter().getItem(position);
		Intent intent = new Intent(getActivity().getApplicationContext(), GroupViewActivity.class);
		intent.putExtra("index", group.getID());
		startActivity(intent);
	}
	
}
