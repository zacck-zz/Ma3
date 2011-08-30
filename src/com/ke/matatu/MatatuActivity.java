package com.ke.matatu;

import com.markupartist.android.widget.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MatatuActivity extends CustomListActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.matatu);

		String[] listItems = { "exploring", "android", "list", "activities" };
		ListAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listItems);
		setListAdapter(adapter);

		ActionBar actionBar = getActionBar(R.id.actionbar, true);
		actionBar.setTitle("Matatu");
	}

	public boolean onOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.matatu_menu, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		finish();
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.matatu_menu_add_route:
			toast("Add route menu item");
			break;
		case R.id.matatu_menu_share:
			share("Subject", "Some message body");
			break;
		case R.id.matatu_menu_directions:
			startActivity(new Intent(MatatuActivity.this,
					LocationsActivity.class));
			break;
		case R.id.matatu_menu_more:
			toast("More options menu item");
			break;
		default:
			toast("Unknown option");
			break;
		}
		return true;
	}

	protected void onListItemClick(ListView listView, View view, int position, long id) {
		Object item = getListAdapter().getItem(position);
		toast("Position "+ String.valueOf(id) + ", Object: "+item.toString());
	}	
}
