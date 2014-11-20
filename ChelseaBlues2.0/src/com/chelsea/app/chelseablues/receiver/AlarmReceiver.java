package com.chelsea.app.chelseablues.receiver;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.chelsea.app.chelseablues.R;
import com.chelsea.app.chelseablues.activity.MainActivity;
import com.chelsea.app.chelseablues.util.PrefUtil;
import com.chelsea.app.chelseablues.util.Utils;

public class AlarmReceiver extends BroadcastReceiver{
	private NotificationManager mNotificationManager;
	private Notification mNotification;
	private AlarmManager mAlarmManager;
	
	public static final int ALARM_TWELVE = 1012;
	public static final int ALARM_THREE = 1003;
	public static final int ALARM_ONE = 1001;
	public static final int ALARM_NOW = 1000;
	
	public static int ALARM_STATE = ALARM_TWELVE;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Intent mIntent = new Intent(context, MainActivity.class);
		if (Utils.isRunningProcess(context, "com.chelsea.app.chelseablues")) {
			mIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_CLEAR_TOP);
		} else {
			mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
		}
		
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, mIntent, 0);
		int alarmTime = intent.getIntExtra("beforetime", 0);
		int requestCode = intent.getIntExtra("requestcode", 1012);
		Log.d("", "requestCode = " + requestCode);
		if (alarmTime != 0) {
			showNotification(context, "알림", alarmTime + "시간 뒤 첼시경기가 시작 됩니다", R.drawable.ic_launcher, pIntent);
		} else {
			showNotification(context, "알림", "지금 경기가 시작했습니다. 첼시의 승리를 위해 열심히 응원해주세요!!!", R.drawable.ic_launcher, pIntent);
		}
		// 알람 해지
		releaseAlarm(context, requestCode);
	}
	
	public void showNotification(Context context, String title, String message, int icon, PendingIntent pendingintent) {
//		if (Utils.isRunningProcess(context, "com.chelsea.app.chelseablues")) {
//			pendingintent = null;
//		}
		if (mNotificationManager == null) {
			mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		} else {
			mNotification = new NotificationCompat.Builder(context)
			.setContentTitle(title)
			.setContentText(message)
			.setSmallIcon(icon)
			.setTicker("경기알림")
			.setAutoCancel(true)
			.setContentIntent(pendingintent)
			.build();
			
			mNotificationManager.notify(7777, mNotification);
		}
	}
	
	// 알람 해제
    public void releaseAlarm(Context context, int requestCode) {
    	Log.d("", "releaseAlarmrequestCode = " + requestCode);
    	mAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
    	
    	Intent Intent = new Intent(context, AlarmReceiver.class);
    	PendingIntent pIntent = PendingIntent.getBroadcast(context, requestCode, Intent, 0);
        
        mAlarmManager.cancel(pIntent);
    }
}
