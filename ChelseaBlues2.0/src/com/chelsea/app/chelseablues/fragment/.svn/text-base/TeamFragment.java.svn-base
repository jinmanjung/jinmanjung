package com.chelsea.app.chelseablues.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.chelsea.app.chelseablues.R;
import com.chelsea.app.chelseablues.activity.TeamActivity;
import com.chelsea.app.chelseablues.adapter.TeamAdapter;
import com.chelsea.app.chelseablues.model.Record;

public class TeamFragment extends FragmentBase {

	private final String TAG = "TeamFragment";
	private View mView = null;
	private TeamActivity mTeamActivity;
	private PlayerFragment mPlayerFragment;
	
	private ListView mTeamListView = null;
	private TeamAdapter mTeamAdapter = null;
	
	private String mPlayerName = "";
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		showProgress("", "Data loading...");
		new TeamInfoTask().execute("");
		setController();
	}

	private void setController() {
		// TODO Auto-generated method stub
		mTeamListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				// add to player fragment
				mTeamActivity = new TeamActivity();
//				mTeamActivity.addItem(2);
				
				// get selected player profile link
				Record rec = mTeamAdapter.getItem(position);
				String link = rec.get("playerLink");
				String playerImage = rec.get("playerImage");
				String playerName = rec.get("playerName");
				// Activity의 커스텀 리스너를 호출함
				customListener.onClicked(link, playerImage, playerName);
				
				// move to profile page
				mTeamActivity.moveToPage(1);
			}
		});
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_team, container, false);
		mView = view;
		mTeamListView = (ListView) mView.findViewById(R.id.lv_team);
		return mView;
	}
	
	// Activity로 데이터를 전달할 커스텀 리스너
	private CustomOnclickListener customListener;
	
	// Activity로 데이터를 전달할 커스텀 리스너의 인터페이스
	public interface CustomOnclickListener {
		public void onClicked(String link, String playerImage, String playerName);
	}
	
	// Activity로 데이터를 전달할 커스텀 리스너를 연결
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		customListener = (CustomOnclickListener) activity;
	}
	
	/**
	 * latest team info
	 */
	private class TeamInfoTask extends AsyncTask<String, Void, ArrayList<Record>> {

		@Override
		protected ArrayList<Record> doInBackground(
				String... params) {
			// TODO Auto-generated method stub
			return mChelseaApplication.getTeamInfo(mContext);
		}

		@Override
		protected void onPostExecute(ArrayList<Record> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// news summary view
			viewTeamInfo(result);
			hideProgress();
		}
	}
	
	private void viewTeamInfo(ArrayList<Record> result) {
		// TODO Auto-generated method stub
		mTeamAdapter = new TeamAdapter(mContext, result);
		mTeamListView.setAdapter(mTeamAdapter);
	}
}
