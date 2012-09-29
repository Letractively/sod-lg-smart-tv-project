package sod.smartphone;

import com.google.android.maps.GeoPoint;

/**
 * 
 * @author MB
 *
 */
public class TVLocation {

	AdminInformation admin;
	
	
	/**
	 * 위도
	 */
	double latitude;
	
	/**
	 * 경도
	 */
	double longitude;
	
	TVLocation(){
		latitude = 0;
		longitude = 0;
	}
	
	TVLocation(double  latitude, double longitude, AdminInformation admin){
		this.latitude = latitude;
		this.longitude = longitude;
		this.admin = admin;
	}
	
	public void setLatitude(double inLatitude){
		latitude = inLatitude;
	}
	
	public void setLogitude(double inLongitude){
		longitude = inLongitude;
	}
	
	public void setTVLocationGeoPoint(double inLatitude, double inLongitude){
		latitude = inLatitude;
		longitude = inLongitude;
	}
	
	public void setTVLocationGeoPint(GeoPoint point){
		latitude = point.getLatitudeE6()/1E6;
		longitude = point.getLongitudeE6()/1E6;
	}
	
	public double getLatitude(){
		return latitude;
	}
	
	public double getLongitude(){
		return longitude;
	}
	
	public GeoPoint getGeoPoint(){
		return new GeoPoint((int)(latitude*1E6) ,(int)(longitude*1E6) );
	}
	
	public AdminInformation getAdminInformation (){
		return admin;
	}
}