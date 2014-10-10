package com.example.grouper;


import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class DiscoverFragment extends ListFragment {
	String[] activities={"Activity 1", "Activity 1", "Activity 2", "Activity 3", "Activity 4", "Activity 5", "Activity 6", "Activity 7", "Activity 8", "Activity 9", "Activity 10"};
	//Call when activity is first created
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		//Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.discoverfragment, container, false);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, activities);
		setListAdapter(adapter);
		return rootView;
	}
}
