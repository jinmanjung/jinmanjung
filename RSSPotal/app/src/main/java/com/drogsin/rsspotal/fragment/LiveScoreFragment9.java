package com.drogsin.rsspotal.fragment;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.drogsin.rsspotal.R;
import com.drogsin.rsspotal.adapter.RSSAdapter;
import com.drogsin.rsspotal.application.RSSPortalApplication;
import com.drogsin.rsspotal.model.RSSItem;
import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.melnykov.fab.FloatingActionButton;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class LiveScoreFragment9 extends ScrollTabHolderFragment implements OnScrollListener  {
	private final String TAG = "LiveScoreFragment9";
	private static View mView;

	private ListView mListView;
	private ArrayList<RSSItem> mList;
	private RSSAdapter mAdapter;
	private ProgressBarCircularIndeterminate circularPb;
	private int mPosition;

	public static LiveScoreFragment9 newInstance() {
		LiveScoreFragment9 f = new LiveScoreFragment9();
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mContext = getActivity();
		View v = inflater.inflate(R.layout.fragment1, null);
		mView = v;

		mListView = (ListView) v.findViewById(R.id.lv_rss);
		circularPb = (ProgressBarCircularIndeterminate) v.findViewById(R.id.progressBarCircularIndeterminate);
		setController();
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mListView.setOnScrollListener(this);}

	private void setController() {
		View placeHolderView = getLayoutInflater(null).inflate(R.layout.view_header_placeholder, mListView, false);
		mListView.addHeaderView(placeHolderView);
		setRSSData();
	}

	public void setRSSData() {
		RSSRequestTask rssTask = new RSSRequestTask();
		rssTask.execute(null, null, null);
	}

	public class RSSRequestTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (circularPb == null) {
				circularPb = (ProgressBarCircularIndeterminate) mView.findViewById(R.id.progressBarCircularIndeterminate);
			}
			circularPb.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(Void... params) {
			mList = new ArrayList<RSSItem>();
			try {
				URL url = new URL(RSSPortalApplication.RSS_URL_DIGITAL);

				XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
				XmlPullParser parser = parserCreator.newPullParser();

				parser.setInput(url.openStream(), null);
				int parserEvent = parser.getEventType();
				String tag = "";
				boolean isItem = false;
				int count = 0;
				HashMap<String, String> map = new HashMap<String, String>();
				do {
					RSSItem item = new RSSItem();
					switch (parserEvent) {
						case XmlPullParser.START_TAG:   //태그가 시작점인지 판단
							tag = parser.getName();
							if ("item".equals(tag)) {
								isItem = true;
							} else if ("enclosure".equals(tag)) {
								map.put("imageUrl", parser.getAttributeValue(null, "url"));
							}
							break;
						case XmlPullParser.TEXT:    // 아이템 안에 데이터를 가지고 오기
							if (isItem) {
								if (!"".equals(parser.getText().trim())) {
									map.put(tag, parser.getText());
								}
							}
							break;
						case XmlPullParser.END_TAG:     //태그가 끝나면 텍스트 파일 가져오지 못하게 하기.
							tag = parser.getName();
							if (isItem) {
								if ("item".equals(tag)) {
									item.setTitle(map.get("title"));
									item.setLink(map.get("link"));
									item.setDescription(map.get("description"));
									item.setImageUrl(map.get("imageUrl"));
									item.setPubDate(map.get("pubDate"));
									item.setCreator(map.get("dc:creator"));
									item.setDate(map.get("dc:date"));
									if (!"".equals(item.getImageUrl()) || item.getImageUrl() != null) {
										Bitmap bitmap = ImageLoader.getInstance().loadImageSync(item.getImageUrl());
										if (bitmap != null) {
											item.setImageHeight(bitmap.getHeight());
										}
									}
									mList.add(count, item);
									map.clear();
									count++;
								}
							}
							break;
					}
					parserEvent = parser.next();    // 다음 태그로 이동
				} while (parserEvent != XmlPullParser.END_DOCUMENT);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			super.onPostExecute(aVoid);
			circularPb.setVisibility(View.GONE);
			mAdapter = new RSSAdapter(mContext, mList);
			if (mListView == null) {
				mListView = (ListView) mView.findViewById(R.id.lv_rss);
			}
			mListView.setAdapter(mAdapter);
		}
	}
	@Override
	public void adjustScroll(int scrollHeight) {
		if (scrollHeight == 0 && mListView.getFirstVisiblePosition() >= 1) {
			return;
		}
		mListView.setSelectionFromTop(1, scrollHeight);
	}


	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		if (mScrollTabHolder != null)
			mScrollTabHolder.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount, 8);
	}
}