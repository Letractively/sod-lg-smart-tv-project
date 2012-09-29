package sod.service;

import java.util.ArrayList;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

public class TVLocationListOverlay extends ItemizedOverlay<OverlayItem> {

	private Context mContext;
	private ArrayList<OverlayItem> mOverlays =
			new ArrayList<OverlayItem>();
	
	public TVLocationListOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		// TODO Auto-generated constructor stub
	}

	public TVLocationListOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
		// TODO Auto-generated constructor stub
	}
	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return mOverlays.get(i);
	}

	//마커를 클릭할때 나오는 이벤트
	@Override
	protected boolean onTap(int index) {
		// TODO Auto-generated method stub
		SODOverlayItem item = (SODOverlayItem)mOverlays.get(index);
		
		//Activity 의 context를 취한후 Intent를 선언한다.
		Intent intent = new Intent(mContext, TVinformationActivity.class);
		
		//선택된 마크(오버레이아이템의 객체)를 가져온다.
		SODOverlayItem sodOverlayItem = (SODOverlayItem) mOverlays.get(index);
		//액티비티간 객체 전달을 위해 Parcelable를 이용한다.
		ParcelableTVLocation parcelableTVLocation = new ParcelableTVLocation(sodOverlayItem.getTVLocation());
		intent.putExtra("tvlocation", parcelableTVLocation);
		//액티비티 호출
		mContext.startActivity(intent);
//		Toast.makeText(mContext, item.getSnippet(), Toast.LENGTH_SHORT).show();
		
		return true;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mOverlays.size();
	}
	
	public void addOverlay(OverlayItem overlay){
		mOverlays.add(overlay);
		populate();
	}

}
