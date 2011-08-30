package com.ke.matatu;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class CustomListActivity extends ListActivity {

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	protected void toast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}
	
	public ActionBar getActionBar(int resource, boolean homeAction) {
		ActionBar actionBar = (ActionBar) findViewById(resource);
		actionBar.addAction(shareAction);
		
		if (homeAction) {
			HomeAction goHomeAction = new HomeAction();
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
    
    private Action shareAction = new Action() {

		@Override
		public int getDrawable() {
			return android.R.drawable.ic_menu_share;
		}

		@Override
		public void performAction(View view) {
			share("Hello world", "Message shared here");
		}
    	
    };

//    private Action goHomeAction = new Action() {
//
//		@Override
//		public int getDrawable() {
//			return R.drawable.ic_title_home_default;
//		}
//
//		@Override
//		public void performAction(View view) {
//			Intent dashboardIntent = new Intent(getApplicationContext(), HomeActivity.class);
//			startActivity(dashboardIntent);
//		}
//    	
//    };
    
    private class HomeAction implements Action {

		@Override
		public int getDrawable() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void performAction(View view) {
			Intent dashboardIntent = new Intent(getApplicationContext(), HomeActivity.class);
			startActivity(dashboardIntent);			
		}
    	
    } // end HomeAction
    
}
