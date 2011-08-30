package com.ke.matatu;

import com.markupartist.android.widget.ActionBar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class ViewReportsActivity extends CustomListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_reports);

		createActivityUI();
	}

	private void createActivityUI() {
		ActionBar actionBar = getActionBar(R.id.actionbar, true);
		actionBar.setTitle("View Reports");

		String[] reports = { "Over charging", "Insultive stickers",
				"Funny incident", "Rude passengers" };
		ListAdapter adapter = new ArrayAdapter<String>(
				ViewReportsActivity.this, android.R.layout.simple_list_item_1,
				reports);
		setListAdapter(adapter);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

}
