package com.chelsea.app.chelseablues.activity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chelsea.app.chelseablues.R;
import com.chelsea.app.chelseablues.model.Record;
import com.chelsea.app.chelseablues.receiver.AlarmReceiver;
import com.chelsea.app.chelseablues.util.PrefUtil;
import com.google.analytics.tracking.android.EasyTracker;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class MainActivity extends ActivityBase implements OnClickListener {

	private final String TAG = "MainActivity";
	
	private int mYear = 2014;
	private int mMonth = 0;
	private int mDay = 0;
	private int mHour = 0;
	private int mMinute = 0;
	private int mSecond = 0;
	
	private TimerTask mTimerTask;
	private AlarmManager mAlarmManager;
	private Calendar mAlarmCalendar;
	
	public static final int ALARM_TWELVE = 1012;
	public static final int ALARM_THREE = 1003;
	public static final int ALARM_ONE = 1001;
	public static final int ALARM_NOW = 1000;
	
	public static int requestCode = 0;
	
	private String matchDate = "";
	
	private AlarmReceiver mAlarmReceiver;
	
	// components
	private CheckBox pushCheckBox;
	private ImageView logoImgIv;
	private TextView oppositTeamTv;
	private TextView matchDateTv;
	private TextView matchStadiumTv;
	private TextView remainTimerTv;
	
	private ImageView newsThumbIv1;
	private TextView newsTitleIv1;
	private TextView newsSummaryIv1;

	private ImageView newsThumbIv2;
	private TextView newsTitleIv2;
	private TextView newsSummaryIv2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// set title bar
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		startActivity(new Intent(this, SplashActivity.class));

		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title);
		
		// check online
		if (!isOnline()) {
			showToast("연결상태를 확인 후 다시 실행해 주세요.");
			finish();
			return;
		}
		
		mAlarmReceiver = new AlarmReceiver();
		
		// parsing thread
		showProgress("", "Data loading...");
		new MainPageTask().execute("");
		setController();
		
		
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	/**
	 * Activity using Google Analytics and EasyTracker.
	 */
	@Override
	protected void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}

	private void setController() {
		((LinearLayout) findViewById(R.id.layout_middle_one)).setOnClickListener(this);
		((LinearLayout) findViewById(R.id.layout_middle_six)).setOnClickListener(this);
		((LinearLayout) findViewById(R.id.layout_middle_seven)).setOnClickListener(this);
		((LinearLayout) findViewById(R.id.layout_news)).setOnClickListener(this);
		
		// next match
		logoImgIv = (ImageView) findViewById(R.id.iv_opposit_logo);
		oppositTeamTv = (TextView) findViewById(R.id.tv_opposit_team);
		matchDateTv = (TextView) findViewById(R.id.tv_match_date);
		matchStadiumTv = (TextView) findViewById(R.id.tv_statium);
		remainTimerTv = (TextView) findViewById(R.id.tv_remain_timer);
		pushCheckBox = (CheckBox) findViewById(R.id.cb_push_onoff);
		pushCheckBox.setChecked(PrefUtil.get(getApplicationContext(), "isPushOn", true));
		
		pushCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					PrefUtil.set(getApplicationContext(), "isPushOn", true);
					// 12시간
					setMultiAlarmTimes(mAlarmCalendar, 12, ALARM_TWELVE);
					// 3시간
					setMultiAlarmTimes(mAlarmCalendar, 3, ALARM_THREE);
					// 1시간
					setMultiAlarmTimes(mAlarmCalendar, 1, ALARM_ONE);
					// 경기시간
					setMultiAlarmTimes(mAlarmCalendar, 0, ALARM_NOW);
					showToast("경기알림푸시 On");
				} else {
					PrefUtil.set(getApplicationContext(), "isPushOn", false);
					mAlarmReceiver.releaseAlarm(getApplicationContext(), ALARM_TWELVE);
					mAlarmReceiver.releaseAlarm(getApplicationContext(), ALARM_THREE);
					mAlarmReceiver.releaseAlarm(getApplicationContext(), ALARM_ONE);
					mAlarmReceiver.releaseAlarm(getApplicationContext(), ALARM_NOW);
					showToast("경기알림푸시 Off");
				}
			}
		});
		
		// news 1
		newsThumbIv1 = (ImageView) findViewById(R.id.iv_news_thumb1);
		newsTitleIv1 = (TextView) findViewById(R.id.tv_news_title1);
		newsSummaryIv1 = (TextView) findViewById(R.id.tv_news_summary1);
		
		// news 2
		newsThumbIv2 = (ImageView) findViewById(R.id.iv_news_thumb2);
		newsTitleIv2 = (TextView) findViewById(R.id.tv_news_title2);
		newsSummaryIv2 = (TextView) findViewById(R.id.tv_news_summary2);
	}

	public void onClick(View v) {
		int id = v.getId();
		Intent intent = null;
		
		switch (id) {
		case R.id.layout_middle_one:
			showToast("서비스 준비중입니다");
			break;
		case R.id.layout_middle_six:
			intent = new Intent(this, TeamActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_middle_seven:
			intent = new Intent(this, MatchActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_news:
			intent = new Intent(this, NewsActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	/**
	 * MainPageTask
	 */
	private class MainPageTask extends AsyncTask<String, Void, ArrayList<Record>> {

		@Override
		protected ArrayList<Record> doInBackground(String... params) {
			// TODO Auto-generated method stub
			return mChelseaApplication.getMainPage(MainActivity.this);
		}

		@Override
		protected void onPostExecute(ArrayList<Record> result) {
			super.onPostExecute(result);

			// news summary view
			setNextMatch();
			viewNewsSummary(result);
			
			Calendar mCalendar = null;
			mCalendar = Calendar.getInstance();
			mCalendar.set(mYear, mMonth - 1, mDay, mHour, mMinute, mSecond);
//			mCalendar.set(mYear, mMonth, 29, 17, 50, 0);
			mAlarmCalendar = null;
			mAlarmCalendar = Calendar.getInstance();
			mAlarmCalendar = mCalendar;
			//set multi alarm
			try {
				if (pushCheckBox.isChecked()) {
					if (PrefUtil.get(mContext, "endAlarm", true)) {
						// 12시간
						setMultiAlarmTimes(mAlarmCalendar, 12, ALARM_TWELVE);
						// 3시간
						setMultiAlarmTimes(mAlarmCalendar, 3, ALARM_THREE);
						// 1시간
						setMultiAlarmTimes(mAlarmCalendar, 1, ALARM_ONE);
						// 경기시작시간
						setMultiAlarmTimes(mAlarmCalendar, 0, ALARM_NOW);
						
						// alarm set off
						PrefUtil.set(mContext, "endAlarm", false);
					}
				}
			timerStart(mCalendar);
			hideProgress();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setMultiAlarmTimes(Calendar alarmCalendar, int beforetime, int requestcode) {
		alarmCalendar.add(alarmCalendar.HOUR_OF_DAY, - beforetime);
//		alarmCalendar.add(alarmCalendar.MINUTE, - beforetime);
		long alarmTime = alarmCalendar.getTimeInMillis();
		String datesFormat = millToDate(alarmTime);
		Log.d(TAG, "datesFormat = " + datesFormat);
		if (PrefUtil.get(getApplicationContext(), "isPushOn", true)) {
			registerAlarm(alarmTime, beforetime, requestcode);
		}
		
		// 알람 셋 후 시갖 원복
		alarmCalendar.add(alarmCalendar.HOUR_OF_DAY, beforetime);
	}
	
	private void setNextMatch() {
		UrlImageViewHelper.setUrlDrawable(logoImgIv, PrefUtil.get(this, "logoImgUrl", ""));
		oppositTeamTv.setText(Html.fromHtml(PrefUtil.get(this, "oppositTeam", "")));
		matchDateTv.setText(Html.fromHtml(PrefUtil.get(this, "matchDate", "")));
		matchStadiumTv.setText(Html.fromHtml(PrefUtil.get(this, "matchStadium", "")));
		
		matchDate = PrefUtil.get(this, "matchDate", "").replace("월", ":").replace("일", ":").replace(" ", "").trim();
		String[] arrMatchDate = matchDate.split(":");
		mMonth = Integer.parseInt(arrMatchDate[0]);
		mDay = Integer.parseInt(arrMatchDate[1]);
		mHour = Integer.parseInt(arrMatchDate[2]);
		mMinute = Integer.parseInt(arrMatchDate[3]);
	}

	public void timerStart(final Calendar calendar) {
		// TODO Auto-generated method stub
		
		mTimerTask = new TimerTask(){

			@Override
			public void run() {
				Calendar mCurrentCalendar = null;
				mCurrentCalendar = Calendar.getInstance();
				// 시간 차이 정보
				long diffTime = Math.abs((calendar.getTimeInMillis() - mCurrentCalendar.getTimeInMillis()))/1000;
				
				// 다음경기까지 남은 시간 정보
				final int[] TIME_UNIT = {86400, 3600, 60, 1}; // 큰 단위를 앞에 놓는다.
				String[] TIME_UNIT_NAME = {":", ":", ":", ":"};
				String remainTime = "";
		        for(int i=0; i < TIME_UNIT.length;i++) { 
		        	remainTime += diffTime/TIME_UNIT[i]+ TIME_UNIT_NAME[i];
		            diffTime %= TIME_UNIT[i];
		        } 
//		        Log.d(TAG, "remainTime = " + remainTime);
		        String[] arrRemainTime = remainTime.split(":");
		        String remainDay = arrRemainTime[0];
		        String remainHour = arrRemainTime[1];
		        String remainMinute = arrRemainTime[2];
		        String remainSecond = arrRemainTime[3];
		        
		        if (Integer.parseInt(remainDay) >= 0) {
			        remainTime = remainDay+"일 "+remainHour+"시 "+remainMinute+"분 "+remainSecond + "초";
		    		timerUpdate(remainTime);
		        } else {
		        	runOnUiThread(new Runnable() {
						
						public void run() {
							remainTimerTv.setText("다음경기 준비중입니다");
							PrefUtil.set(mContext, "endAlarm", true);
						}
					});
		        }
			}
		};
		Timer mTimer = new Timer();
		mTimer.schedule(mTimerTask, 0, 1000);
	}

	private void timerUpdate(final String remainTime) {
		runOnUiThread(new Runnable() {
			
			public void run() {
				remainTimerTv.setText(remainTime);
			}
		});
	}
	
	private void viewNewsSummary(ArrayList<Record> result) {
		String mNewsLink0 = "";
		String mNewsThumbUrl0 = "";
		String mNewsTitle0 = "";
		String mNewsSummary0 = "";
		
		String mNewsLink1 = "";
		String mNewsThumbUrl1 = "";
		String mNewsTitle1 = "";
		String mNewsSummary1 = "";

		for (int i = 0; i < 2; i++) {
			try {
				if (i == 0) {
					mNewsLink0 = result.get(i).get("newsLink");
					mNewsThumbUrl0 = result.get(i).get("newsThumbUrl");
					mNewsTitle0 = result.get(i).get("newsTitle");
					mNewsSummary0 = result.get(i).get("newsSummary");
				} else {
					mNewsLink1 = result.get(i).get("newsLink");
					mNewsThumbUrl1 = result.get(i).get("newsThumbUrl");
					mNewsTitle1 = result.get(i).get("newsTitle");
					mNewsSummary1 = result.get(i).get("newsSummary");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		UrlImageViewHelper.setUrlDrawable(newsThumbIv1, mNewsThumbUrl0);
		newsTitleIv1.setText(Html.fromHtml("<u>" + mNewsTitle0 + "</u>"));
		newsTitleIv1.setEllipsize(TruncateAt.MARQUEE);
		newsTitleIv1.setSelected(true);
		newsSummaryIv1.setText(mNewsSummary0);

		UrlImageViewHelper.setUrlDrawable(newsThumbIv2, mNewsThumbUrl1);
		newsTitleIv2.setText(Html.fromHtml("<u>" + mNewsTitle1 + "</u>"));
		newsTitleIv2.setEllipsize(TruncateAt.MARQUEE);
		newsTitleIv2.setSelected(true);
		newsSummaryIv2.setText(mNewsSummary1);
	}
	
	// 알람 등록
    private void registerAlarm(long second, int beforetime, int requestcode){
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        intent.putExtra("beforetime", beforetime);
        intent.putExtra("requestcode", requestcode);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, requestcode, intent, 0);
        
        mAlarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        String alarmdatesFormat = millToDate(second);
		Log.d(TAG, "alarmdatesFormat = " + alarmdatesFormat);
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, second, pIntent);
    }
    
    @Override
	public void onBackPressed() {
		/*
		 * 앱 종료 여부 체크
		 */
    	showDialog("첼시 블루스", "어플을 종료하시겠습니까?", R.drawable.ic_launcher);
	}
    
    private boolean isOnline() {
    	try {
			ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();		//wifi
			if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
				return true;
			}
			
			State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();		//mobile
			if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
				return true;
			}
    	} catch (NullPointerException e) {
    		return false;
    	}
			
		return false;
		
	}
    
    public String millToDate(long second) {
    	String pattern = "yyyy-MM-dd HH:mm:ss";
    	SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    	String date = formatter.format(new Timestamp(second));
    	return date;
    }
}
