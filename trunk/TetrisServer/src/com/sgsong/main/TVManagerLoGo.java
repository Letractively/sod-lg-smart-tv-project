package com.sgsong.main;

import com.sgsong.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class TVManagerLoGo extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.logo);

		Handler x = new Handler();
		x.postDelayed(new splashhandler(), 300);
	}

	class splashhandler implements Runnable {
		@Override
		public void run() {
			Intent intent = new Intent(TVManagerLoGo.this, TVLocationManagerActivity.class);
			startActivity(intent);
			finish();
		}
	}
}