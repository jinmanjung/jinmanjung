package com.drogsin.rsspotal.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.drogsin.rsspotal.R;
import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class WebViewActivity extends ActivityBase {

	private Toolbar toolbar;
	private TextView actionTitleTv;

	private WebView mWebView;
	private String linkUrl;

	private ProgressBarCircularIndeterminate circularPb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		if (getIntent().getExtras() != null) {
			linkUrl = getIntent().getStringExtra("linkUrl");
		}
		setController();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.mypage, menu);
		return true;
	}

	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	};

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	private void setController() {
		circularPb = (ProgressBarCircularIndeterminate) findViewById(R.id.progressBarCircularIndeterminate);
		setToolbarView();
		viewNews();
		setAdView();
	}

	private void setToolbarView() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		actionTitleTv = (TextView) toolbar.findViewById(R.id.tv_title);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void viewNews() {
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl(linkUrl);
		mWebView.setWebViewClient(new WebViewClientClass());
	}

	private void setAdView() {
		final AdView mAdView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder()
				.build();
		mAdView.setVisibility(View.GONE);
		mAdView.loadAd(adRequest);
		mAdView.setAdListener(new AdListener() {
			@Override
			public void onAdFailedToLoad(int errorCode) {
				super.onAdFailedToLoad(errorCode);
				mAdView.setVisibility(View.GONE);
			}

			@Override
			public void onAdLoaded() {
				super.onAdLoaded();
				mAdView.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAdClosed() {
				super.onAdClosed();
				mAdView.setVisibility(View.GONE);
			}
		});
	}

	 private class WebViewClientClass extends WebViewClient { 
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		 @Override
		 public void onPageFinished(WebView view, String url) {
			 super.onPageFinished(view, url);
		 }

		 @Override
		 public void onPageStarted(WebView view, String url, Bitmap favicon) {
			 circularPb.setVisibility(View.VISIBLE);
			 Handler handler = new Handler();
			 handler.postDelayed(new Runnable() {
				 @Override
				 public void run() {
					 circularPb.setVisibility(View.GONE);
				 }
			 }, 3000);
			 super.onPageStarted(view, url, favicon);
		 }
	 }
}
