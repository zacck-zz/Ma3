package com.ke.matatu.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class TaxiLocatorService extends Service implements LocationListener {

	private TaxiGpsStatusListener taxiGpsStatusListener;

	public TaxiLocatorService() {
		super();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		toast("Service starting...");
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		taxiGpsStatusListener = new TaxiGpsStatusListener();

		Criteria locationProviderSelection = getBestEnabledLocationProvider();
		String bestProvider = locationManager.getBestProvider(
				locationProviderSelection, true);

		locationManager.requestLocationUpdates(bestProvider, MIN_UPDATE_TIME,
				MIN_UPDATE_DISTANCE, TaxiLocatorService.this);

		timer = new Timer("TaxiLocatorService");
		timer.scheduleAtFixedRate(updateTask, 1000L, 1000L);
	}

	private Criteria getBestEnabledLocationProvider() {
		Criteria criteria = new Criteria();
		criteria.setAltitudeRequired(false);
		criteria.setCostAllowed(true);
		criteria.setBearingRequired(false);
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
		return criteria;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "Service destroying");

		locationManager.removeUpdates(TaxiLocatorService.this);
		locationManager.removeGpsStatusListener(taxiGpsStatusListener);

		timer.cancel();
		timer = null;
	}

	private void toast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
				.show();
	}

	private TimerTask updateTask = new TimerTask() {

		@Override
		public void run() {
			updateTaxiLocation();
			Log.d(TAG, "TimerTask is running");
		}

		private void updateTaxiLocation() {
			Log.d(TAG, "Updating Taxi Location");
		}
	};

	private int getGpsSatellitesUsed() {
		int count = 0;
		GpsStatus status = locationManager.getGpsStatus(null);
		Iterable<GpsSatellite> satelittes = status.getSatellites();
		for (GpsSatellite sat : satelittes) {
			if (sat.usedInFix())
				count++;
		}
		return count;
	}

	private class TaxiGpsStatusListener implements GpsStatus.Listener {

		@Override
		public void onGpsStatusChanged(int event) {
			switch (event) {
			case GpsStatus.GPS_EVENT_STARTED:
				Log.d(TAG, "Gps Started");
				break;
			case GpsStatus.GPS_EVENT_FIRST_FIX:
				Log.d(TAG, "Gps First Fix");
			case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
				Log.d(TAG, "Gps Satellite status");
				Log.d(TAG, String.format("There are %d Satelittes used in fix",
						getGpsSatellitesUsed()));
				break;
			case GpsStatus.GPS_EVENT_STOPPED:
				Log.d(TAG, "Gps stopped");
				break;
			default:
				Log.d(TAG, "Unknown status");
			}

		}

	}

	@Override
	public void onLocationChanged(Location location) {
		if (location != null)
			setTaxiLocation(location);
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

	public static void setTaxiLocation(Location taxiLocation) {
		Log.d(TAG,
				String.format(
						"New location found. Provider: %s, Latitude: %f, Longitude: %f, Accuracy: %f",
						taxiLocation.getProvider(), taxiLocation.getLatitude(),
						taxiLocation.getLongitude(), taxiLocation.getAccuracy()));
		TaxiLocatorService.taxiLocation = taxiLocation;
	}

	public static Location getTaxiLocation() {
		return taxiLocation;
	}

	private static Location taxiLocation;
	private static final String TAG = "TaxiLocatorService";
	private static final float MIN_UPDATE_DISTANCE = 0;
	private static final long MIN_UPDATE_TIME = 10000L;
	private LocationManager locationManager;
	private Timer timer;
}
