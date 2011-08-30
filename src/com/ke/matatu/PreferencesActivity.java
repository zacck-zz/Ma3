package com.ke.matatu;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.util.Log;

public class PreferencesActivity extends PreferenceActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.preferences);

		ListPreference updateTimeListPreference = (ListPreference) findPreference("key_update_interval");
		String[] times = { "1 minunte", "3 minutes", "5 minutes",
				"10 minutes" };
		String[] timeValues = { "1", "3", "5", "10" };
		updateTimeListPreference.setEntries(times);
		updateTimeListPreference.setEntryValues(timeValues);
		
		getUserGmailAccount();
	}

	private String getUserGmailAccount() {		
		AccountManager accountManager = (AccountManager) getSystemService(Context.ACCOUNT_SERVICE);
		Account[] accounts = accountManager.getAccountsByType("com.google");

		return ((accounts.length > 0)? accounts[PRIMARY_GMAIL_ACCOUNT].name : null );
	}

	
	@Override
	public void onContentChanged() {
		Log.d("Preferences", "Prefernces content changed");
		super.onContentChanged();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		super.onStop();
		storePreferences();
	}


	private void storePreferences() {
		SharedPreferences sharedPreferences = getSharedPreferences(MATHREE_PREFERENCES, MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("key_first_name", "");
		editor.putString("key_surname", "");
		editor.putString("key_email", getUserGmailAccount());
		editor.putBoolean("key_log_interaction", true);
		editor.putString("key_taxi_auth", "");
		editor.commit();
	}

	private static final String MATHREE_PREFERENCES = "MA3preferences";
	private static final int PRIMARY_GMAIL_ACCOUNT = 0;
}