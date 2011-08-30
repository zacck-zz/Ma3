package com.ke.matatu;

import com.markupartist.android.widget.ActionBar;
import android.os.Bundle;

public class TaxiActivity extends CustomActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taxi);
		
		ActionBar actionBar = getActionBar(R.id.actionbar, true);
		actionBar.setTitle("Taxi");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
