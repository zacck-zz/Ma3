package com.ke.matatu.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//import org.apache.http.HttpConnection;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
import android.util.Log;
//import android.widget.Toast;


public class WebServicesClient {

	public void getConnection(String url) throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url); 
		HttpResponse response = client.execute(httpGet);
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			Log.d("WebServiceClient", line);
		}
	}
	
//	private boolean isConnectionAvailable() {
//		ConnectivityManager conManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
//		
//		return (networkInfo != null && networkInfo.isConnected())? true : false;
//	}
}
