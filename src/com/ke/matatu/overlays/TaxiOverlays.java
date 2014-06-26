package com.ke.matatu.overlays;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class TaxiOverlays extends ItemizedOverlay<OverlayItem> {

	private Context context;

	public TaxiOverlays(Drawable drawable) {
		super(drawable);
		overlaysList = new ArrayList<OverlayItem>();
	}

	public TaxiOverlays(Context context, Drawable drawable) {
		super(drawable);
		overlaysList = new ArrayList<OverlayItem>();
		this.context = context;
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		return overlaysList.get(i);
	}

	@Override
	public int size() {
		return overlaysList.size();
	}

	public void addItem(OverlayItem item) {
		Toast.makeText(context, String.format("Title: %s, Snippet: %s", item.getTitle(), item.getSnippet()), Toast.LENGTH_SHORT).show();
		overlaysList.add(item);
		populate();
	}
	
	private ArrayList<OverlayItem> overlaysList;
}