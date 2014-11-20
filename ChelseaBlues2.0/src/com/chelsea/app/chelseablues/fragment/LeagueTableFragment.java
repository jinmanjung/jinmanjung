package com.chelsea.app.chelseablues.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chelsea.app.chelseablues.R;
import com.chelsea.app.chelseablues.activity.MainActivity;
import com.chelsea.app.chelseablues.adapter.LeagueTableAdapter;
import com.chelsea.app.chelseablues.model.Record;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class LeagueTableFragment extends FragmentBase {
	private final String TAG = "LeagueTableFragment";
	private View mView;
	private LeagueTableAdapter mLeagueTableAdapter = null;
	private ListView mLeagueTableListView;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		showProgress("", "Data loading...");
		new LeagueTableTask().execute("");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_leaguetable, container, false);
		mView = view;
		mLeagueTableListView = (ListView) mView.findViewById(R.id.lv_leaguetable);
		return mView;
	}
	
	/**
	 * LeagueTable
	 */
	private class LeagueTableTask extends AsyncTask<String, Void, ArrayList<Record>> {

		@Override
		protected ArrayList<Record> doInBackground(
				String... params) {
			// TODO Auto-generated method stub
			return mChelseaApplication.getLeagueTable(mContext);
		}

		@Override
		protected void onPostExecute(ArrayList<Record> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// news summary view
			viewLeagueTable(result);
			hideProgress();
		}
	}
	
	private void viewLeagueTable(ArrayList<Record> result) {
		// TODO Auto-generated method stub
		mLeagueTableAdapter = new LeagueTableAdapter(mContext, result);
		mLeagueTableListView.setAdapter(mLeagueTableAdapter);
	}
}
