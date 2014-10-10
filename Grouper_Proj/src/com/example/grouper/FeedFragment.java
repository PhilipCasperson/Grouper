package com.example.grouper;


import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class FeedFragment extends ListFragment implements AdapterView.OnItemClickListener {
	String[] activities={"Activity 1", "Activity 1", "Activity 2", "Activity 3", "Activity 4", "Activity 5", "Activity 6", "Activity 7", "Activity 8"};
	//Call when activity is first created
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.feedfragment, container, false);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, activities);
		setListAdapter(adapter);
		return rootView;
		
	
	}
		public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
			Toast.makeText(getActivity(), "Item" + 1, Toast.LENGTH_SHORT).show();
		}
}
