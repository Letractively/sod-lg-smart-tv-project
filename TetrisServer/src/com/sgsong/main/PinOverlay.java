package com.sgsong.main;

import java.util.ArrayList;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class PinOverlay extends ItemizedOverlay<OverlayItem> {

	private Context mContext;
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	
	public PinOverlay(Drawable defaultMarker) {
		super( boundCenterBottom(defaultMarker));
		// TODO Auto-generated constructor stub
	}
	
	public PinOverlay(Drawable defaultMarker, Context context) {
		super( boundCenterBottom(defaultMarker));
		// TODO Auto-generated constructor stub
		mContext = context;
	}
	
	public void addOverlay(OverlayItem overlay){
		mOverlays.add(overlay);
		populate();
	}
	
	public void clearOverlay(){
		mOverlays.clear();
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mOverlays.size();
	}

}
