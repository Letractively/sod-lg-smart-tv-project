package sod.service;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

import sod.smartphone.TVLocation;

public class SODOverlayItem extends OverlayItem {
	
	TVLocation tvLocation;

	public SODOverlayItem(GeoPoint point, String title, String snippet, TVLocation tvLocation) {
		super(point, title, snippet);
		// TODO Auto-generated constructor stub
		this.tvLocation = tvLocation;
	}
	
	public TVLocation getTVLocation(){
		return tvLocation;
	}

}
