package com.chelsea.app.chelseablues.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chelsea.app.chelseablues.R;
import com.chelsea.app.chelseablues.model.Record;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class NewsAdapter extends BaseAdapter {
	
	private Context mContext = null;
	private ArrayList<Record> mNewsListData = new ArrayList<Record>();
	
	public NewsAdapter(Context mContext, ArrayList<Record> result) {
		super();
		this.mContext = mContext;
		this.mNewsListData = result;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return mNewsListData.size();
	}

	public Record getItem(int position) {
		// TODO Auto-generated method stub
		return mNewsListData.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_news, null);
			holder = new ViewHolder();
			
			holder.newsThumbIv = (ImageView) convertView.findViewById(R.id.iv_news_thumb);
			holder.newsTitleTv = (TextView) convertView.findViewById(R.id.tv_news_title);
			holder.newsSummaryTv = (TextView) convertView.findViewById(R.id.tv_news_summary);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Record rec = mNewsListData.get(position);
		
		UrlImageViewHelper.setUrlDrawable(holder.newsThumbIv, rec.get("newsThumbUrl"));
		holder.newsTitleTv.setText(Html.fromHtml(rec.get("newsTitle")));
		holder.newsSummaryTv.setText(Html.fromHtml(rec.get("newsSummary")));
		
		return convertView;
	}
	
	private class ViewHolder {
		public ImageView newsThumbIv;
		public TextView newsTitleTv;
		public TextView newsSummaryTv;
	}

}
