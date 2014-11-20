package com.chelsea.app.chelseablues.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.chelsea.app.chelseablues.R;


public class SplashActivity extends ActivityBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// set title bar
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		setContentView(R.layout.activity_splash);
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title);
		
		Handler mHandler = new Handler();
		mHandler.postDelayed(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				// parsing thread
				finish();
			}
		}, 3000);
	}
}
