package com.html.app;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.chelsea.app.ChelseaApp.R;
import com.html.net.HTMLParse;

public class News_HTML_Act extends Activity implements OnItemClickListener {

	private static final String TAG = News_HTML_Act.class.getSimpleName();
	private ArrayList<HashMap<String, Object>> data;
	private ListView newsList;
	private CustomAdapter sa;
	private HTMLParse hp;
	private String type = null;
	private ImageView imgView;
	private int mActivityState = 0;
	private static final int ACTIVITY_STATE_INIT = 1;
	private static final int ACTIVITY_STATE_LOADING = 2;
	private static final int ACTIVITY_STATE_PAUSE = 3;
	private static final int ACTIVITY_STATE_STOP = 4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news);
		Log.d(TAG, "onCreate");
		mActivityState = ACTIVITY_STATE_INIT;
		data = new ArrayList<HashMap<String, Object>>();

		newsList = (ListView) findViewById(R.id.newsList);

		imgView = (ImageView) findViewById(R.id.img);
		
		hp = new HTMLParse(News_HTML_Act.this, handler, data);

		sa = new CustomAdapter(News_HTML_Act.this, data, R.layout.news_list,
				new String[] {"newsThumb", "title", "news"},
				new int[] {R.id.newsThumbnail, R.id.title, R.id.news
				});
		newsList.setDivider(new ColorDrawable(Color.WHITE));
		newsList.setDividerHeight(3);
		newsList.setAdapter(sa);
		newsList.setOnItemClickListener(this);
		
		type = "news";
		Log.d(TAG, type);
		hp.open(type);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == event.KEYCODE_BACK){
			Log.d(TAG, "KEYCODE_BACK");
			AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

			dialogBuilder.setTitle(R.string.str_quit);
			dialogBuilder.setMessage(R.string.str_quitquestion);
			dialogBuilder.setPositiveButton(R.string.str_cancel, null);
			dialogBuilder.setNegativeButton(R.string.str_confirm, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			dialogBuilder.show(); 
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG, "onResume");
		Log.d(TAG, "mActivityState = "+mActivityState);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d(TAG, "onPause");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(TAG, "onPause");
		mActivityState = ACTIVITY_STATE_PAUSE;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy");
		if(!data.isEmpty())
			data.clear();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG, "onStop");
		mActivityState = ACTIVITY_STATE_STOP;
	}

	private final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			listUpdate();
		}
	};

	
	private void listUpdate() {
		sa.notifyDataSetChanged();
	}

	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		boolean started = false;
		Log.d(TAG, "onItemClick");
		hp.setmNewsIndex(position);

		ArrayList<HashMap<String, String>> saveData = hp.getSaveData();
		for(int i = 0; i < saveData.size(); i++) {
			if(saveData.get(i).get("index").equals(position+"")) {
				startNewsDetailActivity(saveData.get(i));
				started = true;
			}
		}
		if(!started) {
			hp.open("newsDetail");
		}
	}
	public void startNewsDetailActivity(HashMap<String, String> detail) {
		Intent i = new Intent(this, NewsDetail_HTML_Act.class);
		i.putExtra("title", detail.get("title"));
		i.putExtra("img", detail.get("img"));
		i.putExtra("date", detail.get("date"));
		i.putExtra("contents", detail.get("contents"));
		startActivity(i);
	}
}

