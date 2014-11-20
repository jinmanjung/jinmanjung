package com.chelsea.app.chelseablues.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chelsea.app.chelseablues.R;
import com.chelsea.app.chelseablues.adapter.NewsAdapter;
import com.chelsea.app.chelseablues.adapter.TeamAdapter;
import com.chelsea.app.chelseablues.model.Record;
import com.google.analytics.tracking.android.EasyTracker;

public class NewsActivity extends ActivityBase implements OnClickListener {
	private final String TAG = "NewsActivity";
	
	private ListView mNewsListView = null;
	private NewsAdapter mNewsAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// set title bar
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_news);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title);

		// parsing thread
		showProgress("", "Data loading...");
		new NewsTask().execute("");

		setController();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	/**
	 * Activity using Google Analytics and EasyTracker.
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}

	private void setController() {
		mNewsListView = (ListView) findViewById(R.id.lv_news);
		final Intent intent = new Intent(this, NewsDetailActivity.class);
		mNewsListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				// get selected news link
				Record rec = mNewsAdapter.getItem(position);
				String newsLink = rec.get("newsLink");
				
				intent.putExtra("newsLink", newsLink);
				startActivity(intent);
			}
		});
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		Intent intent = null;
		
		switch (id) {
		case R.id.layout_middle_six:
			intent = new Intent(this, TeamActivity.class);
			startActivity(intent);
			break;
		case R.id.layout_middle_seven:
			intent = new Intent(this, MatchActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	/**
	 * NewsTask
	 */
	private class NewsTask extends AsyncTask<String, Void, ArrayList<Record>> {

		@Override
		protected ArrayList<Record> doInBackground(String... params) {
			// TODO Auto-generated method stub
			return mChelseaApplication.getNewsInfo(NewsActivity.this);
		}

		@Override
		protected void onPostExecute(ArrayList<Record> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			// news summary view
			viewNewsSummary(result);
			hideProgress();
		}
	}

	private void viewNewsSummary(ArrayList<Record> result) {
		// TODO Auto-generated method stub
		mNewsAdapter = new NewsAdapter(this, result);
		mNewsListView.setAdapter(mNewsAdapter);
	}
}
