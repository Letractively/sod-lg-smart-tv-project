package sod.common;



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
	
	
	public TVLocation(){
		latitude = 0;
		longitude = 0;
	}
	
	/**
	 * 위도 경도 값을 받아서 TVLocation 객체를 생성한다.
	 * @param latitude 위도
	 * @param longitude 경도
	 * @param admin 관리자 정보
	 */
	TVLocation(double  latitude, double longitude, AdminInformation admin){
		this.latitude = latitude;
		this.longitude = longitude;
		this.admin = admin;
	}
	
	/**
	 * 위도정보를 셋팅한다.
	 * @param inLatitude 입력할 위도 정보
	 */
	public void setLatitude(double inLatitude){
		latitude = inLatitude;
	}
	
	/**
	 * 경도 정보를 셋팅한다.
	 * @param inLongitude 입력할 경도 정보
	 */
	public void setLogitude(double inLongitude){
		longitude = inLongitude;
	}
	
	/**
	 * 위도와 경도 정보를 동시에 셋팅한다.
	 * @param inLatitude 입력할 위도정보
	 * @param inLongitude 입력할 경도정보
	 */
	public void setTVLocationGeoPoint(double inLatitude, double inLongitude){
		latitude = inLatitude;
		longitude = inLongitude;
	}
	
	/**
	 * 위도 경도 정보를 동시에 셋팅한다.
	 * @param point 위도와 경도 정보가 담긴 GeoPoint 객체
	 */
	public void setTVLocationGeoPoint(GeoPoint point){
		latitude = point.getLatitudeE6()/1E6;
		longitude = point.getLongitudeE6()/1E6;
	}
	
	/**
	 * 위도 정보를 얻는다.
	 * @return 위도
	 */
	public double getLatitude(){
		return latitude;
	}
	
	/**
	 * 경도 정보를 얻는다.
	 * @return 경도
	 */
	public double getLongitude(){
		return longitude;
	}
	
	/**
	 * 위도 경도 정보를 GeoPoint 객체 형태로 받는다.
	 * @return 위도, 경도 정보가 담긴 GeoPoint 객체
	 */
	public GeoPoint getGeoPoint(){
		return new GeoPoint((int)(latitude*1E6) ,(int)(longitude*1E6) );
	}
	
	/**
	 * 관리자 정보를 받는다.
	 * @return 관리자 정보
	 */
	public AdminInformation getAdminInformation (){
		return admin;
	}
}