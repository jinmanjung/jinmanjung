package com.chelsea.app.chelseablues.activity;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.chelsea.app.chelseablues.R;
import com.chelsea.app.chelseablues.model.Record;
import com.google.analytics.tracking.android.EasyTracker;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class NewsDetailActivity extends ActivityBase implements OnClickListener {
	private final String TAG = "NewsDetailActivity";
	private TextView newsTitleTv;
	private ImageView newsImageIv;
	private TextView newsDateTv;
	private TextView newsContentTv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// set title bar
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_news_detail);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title);

		String mNewsLink = getIntent().getStringExtra("newsLink");
		// parsing thread
		showProgress("", "Data loading...");
		new NewsDetailTask().execute(mNewsLink);

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
		newsTitleTv = (TextView) findViewById(R.id.tv_news_title);
		newsImageIv = (ImageView) findViewById(R.id.iv_news_image);
		newsDateTv = (TextView) findViewById(R.id.tv_news_date);
		newsContentTv = (TextView) findViewById(R.id.tv_news_content);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

	/**
	 * NewsDetailTask
	 */
	private class NewsDetailTask extends AsyncTask<String, Void, ArrayList<Record>> {

		@Override
		protected ArrayList<Record> doInBackground(String... params) {
			// TODO Auto-generated method stub
			return mChelseaApplication.getNewsDetailInfo(NewsDetailActivity.this, params[0]);
		}

		@Override
		protected void onPostExecute(ArrayList<Record> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// news detail view
			viewNewsDetail(result);
			hideProgress();
		}
	}

	private void viewNewsDetail(ArrayList<Record> result) {
		// TODO Auto-generated method stub
		String newsTitle = "";
		String newsDate = "";
		String newsImage = "";
		String newsContents = "";
		
		newsTitle = result.get(0).get("newsTitle");
		newsDate = result.get(0).get("newsDate");
		newsImage = result.get(0).get("newsImage");
		newsContents = result.get(0).get("newsContents");
		
		newsTitleTv.setText(newsTitle);
		newsDateTv.setText(newsDate);
		UrlImageViewHelper.setUrlDrawable(newsImageIv, newsImage);
//		newsContentTv.setText(Html.fromHtml(newsContents, null, null));
		newsContentTv.setText(Html.fromHtml(newsContents));
	}
}
