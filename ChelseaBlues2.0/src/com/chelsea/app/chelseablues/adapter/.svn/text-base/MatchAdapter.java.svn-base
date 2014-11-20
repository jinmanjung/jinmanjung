package com.chelsea.app.chelseablues.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chelsea.app.chelseablues.R;
import com.chelsea.app.chelseablues.model.Record;

public class MatchAdapter extends BaseAdapter {

	private Context mContext = null;
	private ArrayList<Record> mMatchData = new ArrayList<Record>();
	
	public MatchAdapter(Context context, ArrayList<Record> result) {
		// TODO Auto-generated constructor stub
		super();
		this.mContext = context;
		this.mMatchData = result;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return mMatchData.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mMatchData.get(position);
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
			convertView = inflater.inflate(R.layout.list_match, null);
			holder = new ViewHolder();
			
			holder.matchDateTv = (TextView) convertView.findViewById(R.id.tv_matchDate);
			holder.kickOffTv = (TextView) convertView.findViewById(R.id.tv_kickOff);
			holder.teamNameHomeTv = (TextView) convertView.findViewById(R.id.tv_teamNameHome);
			holder.scoreHomeTv = (TextView) convertView.findViewById(R.id.tv_scoreHome);
			holder.scoreAwayTv = (TextView) convertView.findViewById(R.id.tv_scoreAway);
			holder.teamNameAwayTv = (TextView) convertView.findViewById(R.id.tv_teamNameAway);
			holder.competitionTv = (TextView) convertView.findViewById(R.id.tv_competition);
//			holder.matchReportTv = (TextView) convertView.findViewById(R.id.tv_matchReport);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Record rec = mMatchData.get(position);
		
		holder.matchDateTv.setText(rec.get("matchDate"));
		holder.kickOffTv.setText(rec.get("kickOff"));
		holder.teamNameHomeTv.setText(rec.get("teamNameHome"));
		holder.scoreHomeTv.setText(rec.get("scoreHome"));
		holder.scoreAwayTv.setText(rec.get("scoreAway"));
		holder.teamNameAwayTv.setText(rec.get("teamNameAway"));
		holder.competitionTv.setText(rec.get("competition"));
//		holder.matchReportTv.setText(rec.get("matchReport"));
		
		if ((position + 1) % 2 == 0) {
			convertView.setBackgroundColor(Color.LTGRAY);
		} else {
			convertView.setBackgroundColor(Color.WHITE);
		}
		
		return convertView;
	}
	
	public class ViewHolder {
		public TextView matchDateTv;
		public TextView kickOffTv;
		public TextView teamNameHomeTv;
		public TextView scoreHomeTv;
		public TextView scoreAwayTv;
		public TextView teamNameAwayTv;
		public TextView competitionTv;
//		public TextView matchReportTv;
	}
}
