package com.ke.matatu;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CustomActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	protected void onStart() {
		super.onStart();
	}
	
	protected void onStop() {
		super.onStop();
	}
	
	protected void onResume() {
		super.onResume();
	}
	
	protected void onRestart() {
		super.onRestart();
	}
	
	protected void onDestroy() {
		super.onDestroy();
	}
	
	protected void toast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}
	
	public void onClickFeature(View view) {
		switch (view.getId()) {
		case R.id.dashboard_button_matatu: 
			startActivity (new Intent(getApplicationContext(), MatatuActivity.class));			
			break; 
		case R.id.dashboard_button_taxi:
			Intent taxiIntent = new Intent(getApplicationContext(), TaxiActivity.class);
			startActivity(taxiIntent);
			break;
		case R.id.dashboard_button_maps:
			startActivity(new Intent(getApplicationContext(), LocationsActivity.class));
			break;
		case R.id.dashboard_button_reports: 
			startActivity(new Intent(getApplicationContext(), ReportActivity.class));
			break;
		default: 
			toast("You pressed an Unknown button");
			break;
		}
	}

	public ActionBar getActionBar(int resource, boolean homeAction) {
		ActionBar actionBar = (ActionBar) findViewById(resource);
		
		HomeAction goHomeAction = new HomeAction();
		
		if (homeAction) {			
			actionBar.setHomeAction(goHomeAction);
		} //end if
		
		return actionBar;
	}
		
    public void share(String subject, String message) {
    	Intent intent = new Intent(Intent.ACTION_SEND);
    	intent.setType("text/plain");
    	intent.putExtra(Intent.EXTRA_SUBJECT, subject);
    	intent.putExtra(Intent.EXTRA_TEXT, message);
    	startActivity(Intent.createChooser(intent, getString(R.string.share)));
    }
    
    private class HomeAction implements Action {

		@Override
		public int getDrawable() {
			return R.drawable.ic_title_home_default;
		}

		@Override
		public void performAction(View view) {
			Intent dashboardIntent = new Intent(getApplicationContext(), HomeActivity.class);
			startActivity(dashboardIntent);
		}
    	
    }
	
}