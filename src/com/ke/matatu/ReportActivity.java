package com.ke.matatu;

import java.io.File;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

public class ReportActivity extends CustomActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);

		ActionBar actionBar = getActionBar(R.id.actionbar, true);
		actionBar.setTitle("Incidents");
		actionBar.addAction(getViewReportsAction());

		String[] incidentTypes = { "Positive", "Overcharging", "Funny",
				"Annoying" };
		ArrayAdapter<String> incidentTyAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, incidentTypes);
		Spinner reportTypeSpinner = (Spinner) findViewById(R.id.incident_type_spinner);
		reportTypeSpinner.setAdapter(incidentTyAdapter);

		ImageButton cameraButton = (ImageButton) findViewById(R.id.camera_button);
		cameraButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				takePicture();
			}
		});

	}

	private Action getViewReportsAction() {
		return (new Action() {
			
			@Override
			public void performAction(View view) {
				Intent viewReportsIntent = new Intent(ReportActivity.this, ViewReportsActivity.class);
				startActivity(viewReportsIntent);
				
			}
			
			@Override
			public int getDrawable() {
				// TODO Auto-generated method stub
				return 0;
			}
		});
	}

	private void takePicture() {
		Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
		File pic = new File(Environment.getExternalStorageDirectory(),
				"Pic.jpg");
		imageLocation = Uri.fromFile(pic);
		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageLocation);
		startActivityForResult(cameraIntent, TAKE_PICTURE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK && requestCode == 0) {
			if (imageLocation != null) {
				Uri pictureFrame = imageLocation;
				getContentResolver().notifyChange(pictureFrame, null);

				imageFrame = (ImageView) findViewById(R.id.imageView);
				ContentResolver resolver = getContentResolver();
				Bitmap bitmap;
				try {
					bitmap = android.provider.MediaStore.Images.Media
							.getBitmap(resolver, pictureFrame);
					imageFrame.setImageBitmap(bitmap);
					toast(pictureFrame.toString());
				} catch (Exception e) {
					toast("Failed to load");
					Log.e(TAG, e.toString());
				}
			}

		}
		super.onActivityResult(requestCode, resultCode, data);
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

	private ImageView imageFrame;
	private static final String TAG = "ReportActivity";
	private static final int TAKE_PICTURE = 0;
	private Uri imageLocation;
}
