package com.ke.matatu.overlays;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class ExampleOverlays extends ItemizedOverlay<OverlayItem> {

	public ExampleOverlays(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		itemsList = new ArrayList<OverlayItem>();
		populate();
	}
	
	public void addOverlay(OverlayItem overlayItem) {
		itemsList.add(overlayItem);
		populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
			return itemsList.get(i);
	}

	@Override
	public int size() {
		return itemsList.size();
	}

	private ArrayList<OverlayItem> itemsList;
}
