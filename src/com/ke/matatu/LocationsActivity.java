package com.ke.matatu;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.ke.matatu.overlays.ExampleOverlays;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class LocationsActivity extends MapActivity implements LocationListener {

	private MyLocationOverlay userLocationOverlay;
	private GeoPoint mapCenter;
	private ExampleOverlays exampleOverlay;
	private List<Overlay> overlaysList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.location_map);

		// Inflate and customize the UI
		createActivityUI();
	}

	private void registerLocationProviders() {
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				MIN_UPDATE_TIME, MIN_UPDATE_DISTANCE, this);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				MIN_UPDATE_TIME, MIN_UPDATE_DISTANCE, this);

		Location lastGPSLocation = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		Location lastNetworkLocation = locationManager
				.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

		if (lastGPSLocation != null && lastNetworkLocation != null)
			if (lastGPSLocation.getTime() < lastNetworkLocation.getTime()) {
				updateLocation(lastGPSLocation);
			} else {
				updateLocation(lastNetworkLocation);
			}

		userLocationOverlay.enableMyLocation();
		overlaysList = map.getOverlays();
		overlaysList.add(userLocationOverlay);
		showTaxisNearby();
	}

	private void showTaxisNearby() {
		Drawable pushpin = getResources().getDrawable(R.drawable.red_pushpin);
		exampleOverlay = new ExampleOverlays(pushpin);

		Double latitude = 33.088584 * 1E6;
		Double longitude = -99.670899 * 1E6;
		GeoPoint taxiLoc = new GeoPoint(latitude.intValue(),
				longitude.intValue());
		OverlayItem taxiItem = new OverlayItem(taxiLoc, "Blue Cabs",
				"0710123456");
		exampleOverlay.addOverlay(taxiItem);
		
		Double lat = 33.0689484 * 1E6;
		Double lng = -96.6710744 * 1E6;
		GeoPoint taxLoc = new GeoPoint(lat.intValue(), lng.intValue());
		OverlayItem taxiItem1 = new OverlayItem(taxLoc, "White Cabs",
				"0713123456");
		exampleOverlay.addOverlay(taxiItem1);
		
		overlaysList.add(exampleOverlay);
		toastMessage("Show Taxis nearby");
	}

	private void updateLocation(Location location) {
		Double lat = location.getLatitude() * 1E6;
		Double lng = location.getLongitude() * 1E6;
		mapCenter = new GeoPoint(lat.intValue(), lng.intValue());

		controller.setCenter(mapCenter);
		if (isCentredAtCurrentLocation())
			controller.animateTo(mapCenter);
	}

	private void removeLocationListeners() {
		locationManager.removeUpdates(this);
		userLocationOverlay.disableMyLocation();
		map.getOverlays().clear();
	}

	private void createActivityUI() {
		actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setTitle("Location");
		actionBar.setHomeAction(goHomeAction);

		showProgressIndicatorActionBar(true);

		MatatuOverlayAction matatuAction = new MatatuOverlayAction();
		actionBar.addAction(matatuAction); // Show Matatu stops

		TaxiOverlayAction taxiAction = new TaxiOverlayAction();
		actionBar.addAction(taxiAction); // Show Taxis nearby

		map = (MapView) findViewById(R.id.mapView);
		map.setBuiltInZoomControls(true);
		map.setStreetView(true);

		// Overlay to show devices current location
		userLocationOverlay = new MyLocationOverlay(LocationsActivity.this, map);

		// Set map center as devices current location
		controller = map.getController();
	} // end createActivityUI

	private void showProgressIndicatorActionBar(boolean display) {
		actionBar.setProgressBarVisibility((display) ? View.VISIBLE
				: View.INVISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.location_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.location_menu_current_location:
			toastMessage("Current Location");
			break;

		case R.id.location_menu_directions:
			toastMessage("Directions");
			break;

		case R.id.location_menu_taxis:
			toastMessage("Taxis");
			break;

		default:
			break;
		}

		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	private void toastMessage(String msg) {
		Toast.makeText(LocationsActivity.this, msg, Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		removeLocationListeners();
		super.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();
		registerLocationProviders();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onPause() {
		removeLocationListeners();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private Action goHomeAction = new Action() {

		@Override
		public int getDrawable() {
			return R.drawable.ic_title_home_default;
		}

		@Override
		public void performAction(View view) {
			Intent dashboardIntent = new Intent(getApplicationContext(),
					HomeActivity.class);
			startActivity(dashboardIntent);
		}

	};

	private class TaxiOverlayAction implements Action {

		@Override
		public int getDrawable() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void performAction(View view) {
			showTaxisNearby();
		}

	}

	private class MatatuOverlayAction implements Action {

		@Override
		public int getDrawable() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void performAction(View view) {
			toastMessage("Matatu overlay will be displayed");
		}

	}

	@Override
	public void onLocationChanged(Location location) {
		// if (location != null)
		// toastMessage(String.format("Latitude: %f , Longitude: %f",
		// location.getLatitude(), location.getLongitude()));

		updateLocation(location);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	public void setCentredAtCurrentLocation(boolean centredCurrentLocation) {
		this.centredCurrentLocation = centredCurrentLocation;
	}

	public boolean isCentredAtCurrentLocation() {
		return centredCurrentLocation;
	}

	private static final long MIN_UPDATE_TIME = 0;
	private static final float MIN_UPDATE_DISTANCE = 0;

	private boolean centredCurrentLocation = false;
	// private static final String TAG = "LocationMapActivity";
	private LocationManager locationManager;
	private MapController controller;
	private ActionBar actionBar;
	private MapView map;
}