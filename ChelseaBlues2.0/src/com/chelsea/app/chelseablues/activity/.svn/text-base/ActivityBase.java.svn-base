package com.chelsea.app.chelseablues.activity;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.chelsea.app.chelseablues.application.ChelseaApplication;

public class ActivityBase extends FragmentActivity {
	
	protected Context mContext;
	protected ChelseaApplication mChelseaApplication;
	private ProgressDialog mProgressDialog;
	private NotificationManager mNotificationManager;
	private Notification mNotification;
	private AlertDialog.Builder builder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = this;
		if (mContext != null)
			mChelseaApplication = (ChelseaApplication) this.getApplication();
	}
	
	public void showToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
	
	public void showProgress(String title, String message) {
		if (mProgressDialog == null) {
			mProgressDialog = ProgressDialog.show(this, title, message);
		} else {
			mProgressDialog.show();
		}
	}
	
	public void hideProgress() {
		if (mProgressDialog != null)
			mProgressDialog.dismiss();
	}
	
	public void showNotification(String title, String message, int icon, PendingIntent pendingintent) {
		if (mNotificationManager == null) {
			mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		} else {
			mNotification = new NotificationCompat.Builder(getApplicationContext())
			.setContentTitle(title)
			.setContentText(message)
			.setSmallIcon(icon)
			.setTicker("알림!!!")
			.setAutoCancel(true)
			.setContentIntent(pendingintent)
			.build();
			
			mNotificationManager.notify(7777, mNotification);
		}
		
	}
	
	public void showDialog(String title, String message, int icon) {
		if (builder == null) {
			builder = new AlertDialog.Builder(this); 
		} else {
			builder.setTitle(title)
			.setMessage(message)
			.setIcon(icon)
			.setNegativeButton("아니오", new OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			})
			.setPositiveButton("예", new OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			})
			.show();
		}
	}
}
