package com.ke.matatu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ListAdapter;

import com.markupartist.android.widget.ActionBar;

public class TaxiActivity extends CustomListActivity {

	class Parser extends AsyncTask<String, Integer, JSONArray> {

		private ProgressDialog progressDialog = null;
		
		@Override
		protected void onPreExecute() {			
			super.onPreExecute();
			
			progressDialog = new ProgressDialog(TaxiActivity.this);
			progressDialog.setMessage("Locating Taxis nearby");
			progressDialog.show();
		}

		@Override
		protected JSONArray doInBackground(String... params) {
			JSONArray taxiArray = null;
			JSONObject json = getTaxisNearby();
			
			try {
				taxiArray = json.getJSONArray("taxis");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return taxiArray;
//			return params[0];
		}

		@Override
		protected void onPostExecute(JSONArray result) {
			super.onPostExecute(result);
			
			for (int i = 0; i < result.length(); i++) {
				try {
					JSONObject taxiObject = result.getJSONObject(i);
					
					//Get elements of the taxi object
					JSONObject taxiElements = taxiObject.getJSONObject("taxi");
					toast(taxiElements.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				} finally {
					closeDialog();
				}
			}
		}

		private void closeDialog() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				progressDialog.dismiss();
			}			
		}
		
		
	} // end class
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taxi);
		
		createActivityUI();
		
		client = new DefaultHttpClient();
		
		if (isConnectionAvailable()) {
			new Parser().execute("authkey");
		}
	}

	// Creates UI elements
	private void createActivityUI() {
		ActionBar actionBar = getActionBar(R.id.actionbar, true);
		actionBar.setTitle("Taxi");

		getTaxiList();
		ListAdapter adapter = new Ma3ListAdapter(TaxiActivity.this, taxiList);
		setListAdapter(adapter);
		
		toast("Sdcard is mounted: " + isSDcardMounted());
		
		File sdcard = Environment.getExternalStorageDirectory();
		toast(sdcard.getAbsolutePath());
	}

	public JSONObject getTaxisNearby() {
		JSONObject jsonResponse = null;
		StringBuilder url = new StringBuilder(URL);
		url.append("api.php?task=taxi&latitude=1&longitude=1");
		
		HttpGet request = new HttpGet(url.toString());
		HttpResponse response = null;
		
		try {
			response = client.execute(request);
			
			int statusCode = response.getStatusLine().getStatusCode();
						
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				String data = EntityUtils.toString(entity);
				
				jsonResponse = new JSONObject(data);				
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		
		return jsonResponse;
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
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	private void getTaxiList() {
		taxiList = new ArrayList<Model>();
		addTaxiItem("/sdcard/Pic.jpg", "Green Cabs", "at Adams arcade");
		addTaxiItem("/sdcard/Pic.jpg", "Blue Cabs", "at Uchumi Ngong hyper");
		addTaxiItem("/sdcard/Pic.jpg", "Red Cabs", "at Argwings Khodhek");
	}
	
	private void addTaxiItem(String icon, String title, String summary) {
		taxi = new Model(icon, title, summary);
		taxiList.add(taxi);
	}
	
	private boolean isConnectionAvailable() {
		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
		 
		if ( networkInfo != null && networkInfo.isConnectedOrConnecting())
			return true;
		else
			return false;
	}

	private boolean isSDcardMounted() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	
	private Model taxi;
	private ArrayList<Model> taxiList;
	private static final String URL = "http://192.168.1.103/mathree/";
	private HttpClient client;
}
