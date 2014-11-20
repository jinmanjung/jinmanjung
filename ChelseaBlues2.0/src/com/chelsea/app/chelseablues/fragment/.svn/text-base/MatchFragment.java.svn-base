package com.chelsea.app.chelseablues.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.chelsea.app.chelseablues.R;
import com.chelsea.app.chelseablues.activity.MatchActivity;
import com.chelsea.app.chelseablues.adapter.MatchAdapter;
import com.chelsea.app.chelseablues.application.ChelseaApplication;
import com.chelsea.app.chelseablues.model.Record;
import com.chelsea.app.chelseablues.util.PrefUtil;

public class MatchFragment extends FragmentBase implements OnItemClickListener {
	private final String TAG = "MatchFragment";
	private MatchActivity mMatchActivity;
	private View mView;
	private MatchAdapter mMatchAdapter = null;
	private ListView mMatchListView;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		new MatchInfoTask().execute("");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_match, container, false);
		mView = view;
		mMatchListView = (ListView) mView.findViewById(R.id.lv_leaguetable);
		mMatchListView.setOnItemClickListener(this);
		return mView;
	}

	/**
	 * MatchInfoTask
	 */
	private class MatchInfoTask extends
			AsyncTask<String, Void, ArrayList<Record>> {

		@Override
		protected ArrayList<Record> doInBackground(String... params) {
			// TODO Auto-generated method stub
			return mChelseaApplication.getMatchInfo(mContext);
		}

		@Override
		protected void onPostExecute(ArrayList<Record> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// news summary view
			viewMatchInfo(result);
		}
	}

	private void viewMatchInfo(ArrayList<Record> result) {
		// TODO Auto-generated method stub
		mMatchAdapter = new MatchAdapter(mContext, result);
		mMatchListView.setAdapter(mMatchAdapter);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		// add to player fragment
		mMatchActivity = new MatchActivity();
//		mMatchActivity.addItem(2);
		// get list items
		Record item = (Record) parent.getItemAtPosition(position);
		String mMatchreport = item.get("matchReport");
		if (mMatchreport != "") {
			mMatchreport = ChelseaApplication.MAIN_URL + mMatchreport.replace("<a href=\"", "").replace("\"><img src=\"/images/btn_report_kr.gif\" /></a>", "");
			String[] splitMatch = mMatchreport.split("/");
			int mNumber = Integer.parseInt(splitMatch[6]);
			String changedNumber = String.valueOf(mNumber - 4);
			mMatchreport = splitMatch[0] + "/" +splitMatch[1] + "/" +splitMatch[2] + "/" + splitMatch[3] + "/" + splitMatch[4] + "/" + splitMatch[5] + "/" + changedNumber;
			mMatchreport = mMatchreport.replace("match/report", "matchgallery");
			
			Log.d(TAG, "mMatchreport = " + mMatchreport);
			PrefUtil.set(mContext, "matchReport", mMatchreport);
		
			// Activity의 커스텀 리스너를 호출함
			customListener.onClicked(mMatchreport);
			
			// move to profile page
			mMatchActivity.moveToPage(2);
		} else {
			showToast("경기 결과가 없습니다");
			return;
		}
		// Intent intent = new Intent(mContext, MatchDetailActivity.class);
		// startActivity(intent);
	}

	// Activity로 데이터를 전달할 커스텀 리스너
	private CustomOnclickListener customListener;

	// Activity로 데이터를 전달할 커스텀 리스너의 인터페이스
	public interface CustomOnclickListener {
		public void onClicked(String matchReport);
	}

	// Activity로 데이터를 전달할 커스텀 리스너를 연결
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		customListener = (CustomOnclickListener) activity;
	}
}
