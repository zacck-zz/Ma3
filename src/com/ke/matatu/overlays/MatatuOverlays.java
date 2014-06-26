package com.ke.matatu.overlays;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MatatuOverlays extends ItemizedOverlay<OverlayItem> {

	public MatatuOverlays(Drawable drawable) {
		super(drawable);
		matatusList = new ArrayList<OverlayItem>();
	}

	public MatatuOverlays(Context context, Drawable drawable) {
		super(drawable);
		matatusList = new ArrayList<OverlayItem>();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		return matatusList.get(i);
	}

	@Override
	public int size() {
		return matatusList.size();
	}

	public void addItem(OverlayItem item) {
		matatusList.add(item);
		populate();
	}
	
	private List<OverlayItem> matatusList;
}
