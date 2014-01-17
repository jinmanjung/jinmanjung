package com.html.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.chelsea.app.ChelseaApp.R;

public class CustomAdapter extends SimpleAdapter{
	private static final String TAG = News_HTML_Act.class.getSimpleName();
	
	public CustomAdapter(Context context, List<? extends Map<String, ?>> data,
			int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = super.getView(position, convertView, parent);
		Log.d(TAG, "getView");
		HashMap<String, Object> hm = (HashMap<String, Object>) super.getItem(position);

		ImageView image = (ImageView) v.findViewById(R.id.newsThumbnail);
		TextView txt1 = (TextView) v.findViewById(R.id.title);
		TextView txt2 = (TextView) v.findViewById(R.id.news);

		image.setImageDrawable((Drawable) hm.get("newsThumb"));
		txt1.setText(hm.get("title").toString());
		txt2.setText(hm.get("news").toString());
		return v;
	}
}
