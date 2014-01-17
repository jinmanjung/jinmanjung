package com.html.app;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chelsea.app.ChelseaApp.R;

public class MatchDetail_HTML_Act extends Activity {
	private static final String TAG = MatchDetail_HTML_Act.class.getSimpleName();
	private static final String origURL = "http://korea.chelseafc.com";
	private static final int SUMMARY_VIEW = 1;

	private TextView mTvSummary;
	private String mSummary;

	private ArrayList<Drawable> mImageList;
	private int mListCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.match_detail);

		mSummary = null;

		mImageList = new ArrayList<Drawable>();
		mListCount = 0;

		mTvSummary = (TextView) findViewById(R.id.match_summary);
	}

	private Handler postHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == SUMMARY_VIEW) {
				Spanned sp = (Spanned) msg.obj;
				mTvSummary.setText(sp);
			}
		}
	};

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Intent intent = getIntent();

		mSummary = intent.getStringExtra("summary");

		final ImageGetter imageGetter = new ImageGetter() {
			public Drawable getDrawable(String source) {
				// TODO Auto-generated method stub
				Drawable d = null;
				Point OutSize = new Point();
				getWindowManager().getDefaultDisplay().getSize(OutSize);

				try {
					d = Drawable.createFromStream((InputStream) new URL(origURL + source).getContent(), "src name");
					int imageResizeHeight = resizeImage(d);
					d.setBounds(0, 0, OutSize.x, imageResizeHeight);
					mImageList.add(mListCount++, d);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return d;
			}
		};

		new Thread() {
			public void run() { // contents image download in imageGetter
				Message msg = new Message();
				Spanned spSummary = Html.fromHtml(mSummary, imageGetter, null);

				msg.what = SUMMARY_VIEW;
				msg.obj = spSummary;
				postHandler.sendMessage(msg);

			}
		}.start();

		if ("".equals(mSummary)) {
			mTvSummary.setVisibility(View.GONE);
		} else {
			mTvSummary.setVisibility(View.VISIBLE);
		}
	}

	public int resizeImage(Drawable d) {

		Point OutSize = new Point();
		getWindowManager().getDefaultDisplay().getSize(OutSize);

		int deviceWidth = OutSize.x;
		float rate = 0;
		int height = 0;

		rate = (float) deviceWidth / (float) d.getIntrinsicWidth();
		height = (int) (d.getIntrinsicHeight() * rate);

		return height;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		Log.i(TAG, "onConfigurationChanged() called");
		
		ImageGetter imageReGetter = new ImageGetter() {	// ImageGetter for rotation (use saved image)
			
			public Drawable getDrawable(String source) {
				// TODO Auto-generated method stub
				Drawable d = null;
				
				if(mListCount < mImageList.size()) {
					d = mImageList.get(mListCount);
				}
				
				if(d != null) {
					Point OutSize = new Point();
					getWindowManager().getDefaultDisplay().getSize(OutSize);
					
					int imageResizeHeight = resizeImage(d);
					d.setBounds(0, 0, OutSize.x, imageResizeHeight);
					mListCount++;
				}
				return d;
			}
		};

		if(mListCount != 0) {		// contents image resize for rotation
			mListCount = 0;
			mTvSummary.setText(Html.fromHtml(mSummary, imageReGetter, null));
		}
	}
}
