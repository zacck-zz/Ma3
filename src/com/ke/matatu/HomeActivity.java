package com.ke.matatu;

import com.ke.matatu.service.TaxiLocatorService;
import com.markupartist.android.widget.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class HomeActivity extends CustomActivity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);        
        
        ActionBar actionBar = getActionBar(R.id.actionbar, false);
        actionBar.setTitle("Matatu");
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.dashboard_menu, menu);
    	return true;
    }
    
    @Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.finish();
	}

	public boolean onOptionsItemSelected(MenuItem item) {
    	Intent serviceIntent = new Intent(HomeActivity.this, TaxiLocatorService.class);
    	    	
		switch (item.getItemId()) {
		case R.id.dashboard_menu_direction:
			toast("Directions Menu");
			startService(serviceIntent);
			break;
		case R.id.dashboard_menu_share:
//			share("Subject", "Some message body");
			stopService(serviceIntent);
			break;
		case R.id.dashboard_menu_preferences:
			startActivity(new Intent(HomeActivity.this, PreferencesActivity.class));
			break;
		default:
			toast("Unknown option");
			break;
		}
    	return true;
    }

	
}