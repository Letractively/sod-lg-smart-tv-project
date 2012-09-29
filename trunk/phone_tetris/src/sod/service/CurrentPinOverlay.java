package sod.service;



import android.content.Context;
import android.widget.Toast;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;


public class CurrentPinOverlay extends 	MyLocationOverlay{
	
	Context mContext;

	public CurrentPinOverlay(Context arg0, MapView arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		mContext = arg0;
	}

	@Override
	protected boolean dispatchTap() {
		// TODO Auto-generated method stub
		Toast.makeText(mContext, "여기가 현재위치입니다.",  Toast.LENGTH_SHORT).show();
		return false;
	}
	

}
