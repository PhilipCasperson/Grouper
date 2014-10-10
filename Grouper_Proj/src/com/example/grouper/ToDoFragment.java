package com.example.grouper;


import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ToDoFragment extends ListFragment {
	String[] activities={"todo 1", "todo 1", "todo 2", "todo 3", "todo 4", "todo 5", "todo 6", "todo 7", "todo 8"};
	//Call when todo is first created
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		//Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.todofragment, container, false);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, activities);
		setListAdapter(adapter);
		return rootView;
	}
}