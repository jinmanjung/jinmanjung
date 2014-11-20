package com.chelsea.app.chelseablues.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chelsea.app.chelseablues.R;
import com.chelsea.app.chelseablues.model.Record;
import com.chelsea.app.chelseablues.util.Utils;

public class LeagueTableAdapter extends BaseAdapter {

	private Context mContext = null;
	private ArrayList<Record> mLeagueTableData = new ArrayList<Record>();
	
	public LeagueTableAdapter(Context context, ArrayList<Record> result) {
		// TODO Auto-generated constructor stub
		super();
		this.mContext = context;
		this.mLeagueTableData = result;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return mLeagueTableData.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mLeagueTableData.get(position);
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
			convertView = inflater.inflate(R.layout.list_leaguetable, null);
			holder = new ViewHolder();
			
			holder.rankTv = (TextView) convertView.findViewById(R.id.tv_rank);
			holder.teamTv = (TextView) convertView.findViewById(R.id.tv_team);
			holder.playedTv = (TextView) convertView.findViewById(R.id.tv_played);
			holder.winTv = (TextView) convertView.findViewById(R.id.tv_hWin);
			holder.drawTv = (TextView) convertView.findViewById(R.id.tv_hDraw);
			holder.loseTv = (TextView) convertView.findViewById(R.id.tv_hLose);
			holder.scoreTv = (TextView) convertView.findViewById(R.id.tv_hScore);
			holder.losePointTv = (TextView) convertView.findViewById(R.id.tv_hLosePoint);
//			holder.aWinTv = (TextView) convertView.findViewById(R.id.tv_aWin);
//			holder.aDrawTv = (TextView) convertView.findViewById(R.id.tv_aDraw);
//			holder.aLoseTv = (TextView) convertView.findViewById(R.id.tv_aLose);
//			holder.aScoreTv = (TextView) convertView.findViewById(R.id.tv_aScore);
//			holder.aLosePointTv = (TextView) convertView.findViewById(R.id.tv_aLosePoint);
			holder.goalDifferenceTv = (TextView) convertView.findViewById(R.id.tv_goalDifference);
			holder.pointTv = (TextView) convertView.findViewById(R.id.tv_point);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Record rec = mLeagueTableData.get(position);
		//add home & away point
		int win = Integer.parseInt(rec.get("hWin")) + Integer.parseInt(rec.get("aWin"));
		int draw = Integer.parseInt(rec.get("hDraw")) + Integer.parseInt(rec.get("aDraw"));
		int lose = Integer.parseInt(rec.get("hLose")) + Integer.parseInt(rec.get("aLose"));
		int score = Integer.parseInt(rec.get("hScore")) + Integer.parseInt(rec.get("aScore"));
		int losePoint = Integer.parseInt(rec.get("hLosePoint")) + Integer.parseInt(rec.get("aLosePoint"));
//		Log.d("test", "newswin = " + win);
//		String renameTeams = setRenameTeam(rec.get("team"), mContext);
		
		// exception 
				
		holder.rankTv.setText(Html.fromHtml(rec.get("rank")));
		holder.teamTv.setText(Html.fromHtml(rec.get("team")));
		holder.playedTv.setText(Html.fromHtml(rec.get("played")));
		holder.winTv.setText(Html.fromHtml(String.valueOf(win)));
		holder.drawTv.setText(Html.fromHtml(String.valueOf(draw)));
		holder.loseTv.setText(Html.fromHtml(String.valueOf(lose)));
		holder.scoreTv.setText(Html.fromHtml(String.valueOf(score)));
		holder.losePointTv.setText(Html.fromHtml(String.valueOf(losePoint)));
//		holder.aWinTv.setText(Html.fromHtml(rec.get("aWin")));
//		holder.aDrawTv.setText(Html.fromHtml(rec.get("aDraw")));
//		holder.aLoseTv.setText(Html.fromHtml(rec.get("aLose")));
//		holder.aScoreTv.setText(Html.fromHtml(rec.get("aScore")));
//		holder.aLosePointTv.setText(Html.fromHtml(rec.get("aLosePoint")));
		holder.goalDifferenceTv.setText(Html.fromHtml(rec.get("goalDifference")));
		holder.pointTv.setText(Html.fromHtml(rec.get("point")));
		
		if ((position + 1) % 2 == 0) {
			convertView.setBackgroundColor(Color.LTGRAY);
		} else {
			convertView.setBackgroundColor(Color.WHITE);
		}
		
		return convertView;
	}
	
	public class ViewHolder {
		public TextView rankTv;
		public TextView teamTv;
		public TextView playedTv;
		public TextView winTv;
		public TextView drawTv;
		public TextView loseTv;
		public TextView scoreTv;
		public TextView losePointTv;
//		public TextView aWinTv;
//		public TextView aDrawTv;
//		public TextView aLoseTv;
//		public TextView aScoreTv;
//		public TextView aLosePointTv;
		public TextView goalDifferenceTv;
		public TextView pointTv;
	}
}
