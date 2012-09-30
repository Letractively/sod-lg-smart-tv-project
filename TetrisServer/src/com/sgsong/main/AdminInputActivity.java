package com.sgsong.main;

import com.sgsong.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
public class AdminInputActivity extends Activity {
	EditText TvName,PhoneNumber,ServiceName,e_Mail;
	ImageButton admininputButton;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.admin_input);
		setContentView(R.layout.admin_input);
		
		admininputButton =(ImageButton)findViewById(R.id.sendAdminInfo);
		 TvName=(EditText)findViewById(R.id.tvNameEditText);
		 e_Mail=(EditText)findViewById(R.id.eMailEditText);
		 PhoneNumber=(EditText)findViewById(R.id.phoneNumberEditText);
		 ServiceName=(EditText)findViewById(R.id.serviceNameEditText);
		TvName.setText("HansungTV for gcc");
		e_Mail.setText("karima@hansung.ac.kr");
		PhoneNumber.setText("010-4282-3753");
		ServiceName.setText("GCC");
		admininputButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(AdminInputActivity.this).setTitle("확인창").setMessage("올바르게\n입력하셨습니까?")
				.setPositiveButton("확인",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(AdminInputActivity.this, com.sgsong.main.controller.class);
						startActivity(intent);
					}
				}).setNegativeButton("취소",new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						TvName.setText("");
						e_Mail.setText("");
						PhoneNumber.setText("");
						ServiceName.setText("");
					}
				}).show();
			}
		});
	}

}
