package com.example.grouper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MapActivity extends Activity {

	//Google Map
	private GoogleMap googleMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		try {
			//loading map
			initializeMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initializeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			//set map to UC
			LatLng uniCin = new LatLng(39.133122, -84.514950);
			
			//dummy data
			LatLng meeting1 = new LatLng(39.132925, -84.516475);
			LatLng meeting2 = new LatLng(39.131989, -84.515123);
			LatLng meeting3 = new LatLng(39.131390, -84.511808);
			LatLng meeting4 = new LatLng(39.127531, -84.516568);
			LatLng meeting5 = new LatLng(39.128136, -84.517617);
			LatLng meeting6 = new LatLng(39.127832, -84.514144);
			
			googleMap.setMyLocationEnabled(true);
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uniCin, 15));
			
			googleMap.addMarker(new MarkerOptions()
				.title("Discuss Midterm")
				.snippet("8:30, Database Group")
				.position(meeting1));
			googleMap.addMarker(new MarkerOptions()
			.title("Work on Final")
			.snippet("6:00, Android Dev Group")
			.position(meeting2));
			googleMap.addMarker(new MarkerOptions()
			.title("DX Chapter Meeting")
			.snippet("8:30")
			.position(meeting3));
			googleMap.addMarker(new MarkerOptions()
			.title("Discuss Upcoming Goals")
			.snippet("4:00, iPhone Dev Group")
			.position(meeting4));
			//Check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		initializeMap();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuitem_search:
			Intent intent = new Intent(this, SearchableActivity.class);
			startActivity(intent);
			return true;
		case R.id.menuitem_new_todo:
			Intent todoIntent = new Intent(this, TodoActivity.class);
			startActivity(todoIntent);
			return true;
		}
		return false;
	}
}
