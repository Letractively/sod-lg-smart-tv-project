package sod.service;

import sod.smartphone.AdminInformation;
import sod.smartphone.TVLocation;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TVinformationActivity extends Activity {

	TVLocation tvLocation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tvinformation);
		
		ParcelableTVLocation parcelableTVLocation = getIntent().getParcelableExtra("tvlocation");
		tvLocation =  parcelableTVLocation.getTvLocation();
		
		TextView tvNameTextView= (TextView)findViewById(R.id.tvNameTextView);
		TextView e_mailTextView = (TextView)findViewById(R.id.e_mailTextView);
		TextView phoneNumberTextView = (TextView)findViewById(R.id.phoneNumberTextView);
		TextView  serviceNameTextView = (TextView)findViewById(R.id.serviceNameTextView);
		
		AdminInformation admin = tvLocation.getAdminInformation();
		
		tvNameTextView.setText(admin.TVname);
		e_mailTextView.setText(admin.e_mailAddress);
		phoneNumberTextView.setText(admin.phoneNumber);
		serviceNameTextView.setText(admin.serviceName);
		
		
	}

}
