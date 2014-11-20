package com.chelsea.app.chelseablues.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chelsea.app.chelseablues.R;
import com.chelsea.app.chelseablues.model.Record;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class TeamAdapter extends BaseAdapter {
	
	private Context mContext = null;
	private ArrayList<Record> mTeamListData = new ArrayList<Record>();
	
	public TeamAdapter(Context mContext, ArrayList<Record> result) {
		super();
		this.mContext = mContext;
		this.mTeamListData = result;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return mTeamListData.size();
	}

	public Record getItem(int position) {
		// TODO Auto-generated method stub
		return mTeamListData.get(position);
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
			convertView = inflater.inflate(R.layout.list_team, null);
			holder = new ViewHolder();
			
			holder.mPlayerImage = (ImageView) convertView.findViewById(R.id.iv_team_profile);
			holder.mName = (TextView) convertView.findViewById(R.id.tv_team_name);
			holder.mPosionImage = (ImageView) convertView.findViewById(R.id.iv_team_position);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Record rec = mTeamListData.get(position);
		String mPlayerName = rec.get("playerName");
		
		setPlayerPosition(mPlayerName, holder);
		UrlImageViewHelper.setUrlDrawable(holder.mPlayerImage, rec.get("playerImage"));
		holder.mName.setText(mPlayerName);
		
		return convertView;
	}
	
	private class ViewHolder {
		public ImageView mPlayerImage;
		public TextView mName;
		public ImageView mPosionImage;
	}

	// 선수별  포지션 지정
	public void setPlayerPosition(String playername, ViewHolder holder) {
		if (playername.contains("레미") || playername.contains("쉬를레") || playername.contains("드로그바") || playername.contains("코스타")) {
			holder.mPosionImage.setImageResource(R.drawable.position_fw);
		} else if (playername.contains("파브레가스") || playername.contains("하미레스") || playername.contains("아자르") || playername.contains("오스카")
				|| playername.contains("미켈") || playername.contains("살라") || playername.contains("힌켈") || playername.contains("마티치")
				|| playername.contains("윌리안")) {
			holder.mPosionImage.setImageResource(R.drawable.position_mf);
		} else if (playername.contains("아케") || playername.contains("이바노비치") || playername.contains("루이스") || playername.contains("조우마")
				|| playername.contains("케이힐") || playername.contains("테리") || playername.contains("아스필리쿠에타") || playername.contains("크리스천슨")) {
			holder.mPosionImage.setImageResource(R.drawable.position_df);
		} else if (playername.contains("체흐") || playername.contains("쿠르투아") || playername.contains("슈워처")) {
			holder.mPosionImage.setImageResource(R.drawable.position_gk);
		}
	}
}
