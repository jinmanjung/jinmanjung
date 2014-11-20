package com.chelsea.app.chelseablues.fragment;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chelsea.app.chelseablues.R;
import com.chelsea.app.chelseablues.application.ChelseaApplication;
import com.chelsea.app.chelseablues.model.Record;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class PlayerFragment extends FragmentBase {
	private final String TAG = "PlayerFragment";
	private View mView;
	private TextView nationTv;
	private TextView birthDateTv;
	private TextView heightTv;
	private TextView weightTv;
	private TextView backNumberTv;
	private TextView contactDateTv;
	private TextView beforeClubTv;
	private TextView positionTv;
	
	private ImageView playerImageIv;
	
	private String mPlayerImage = "";
	private String mPlayerName = "";

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		new PlayerInfoTask().execute(ChelseaApplication.DEFAULT_PLAYER_URL);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_player, container, false);
		mView = view;
		return mView;
	}
	
	/**
	 * latest player info
	 */
	private class PlayerInfoTask extends AsyncTask<String, Void, ArrayList<Record>> {

		@Override
		protected ArrayList<Record> doInBackground(
				String... params) {
			// TODO Auto-generated method stub
			return mChelseaApplication.getPlayerInfo(mContext, params[0]);
		}

		@Override
		protected void onPostExecute(ArrayList<Record> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// news summary view
			viewPlayer(result);
			hideProgress();
		}
	}
	
	private void viewPlayer(ArrayList<Record> result) {
		// TODO Auto-generated method stub
		if (result != null) {
			String mNation = result.get(0).get("nation");				// 국적
			String mBirthDate = result.get(0).get("birthDate");			// 생년월일
			String mHeight = result.get(0).get("height");				// 키
			String mWeight = result.get(0).get("weight");				// 체중
			String mBackNumber = result.get(0).get("backNumber");		// 등번호
			String mContactDate = result.get(0).get("contactDate");		// 가입일자
			String mBeforeClub = result.get(0).get("beforeClub");		// 이전클럽
			String mPosition = result.get(0).get("position");			// 포지션
			
			nationTv = (TextView) mView.findViewById(R.id.tv_nation);
			birthDateTv = (TextView) mView.findViewById(R.id.tv_birthdate);
			heightTv = (TextView) mView.findViewById(R.id.tv_height);
			weightTv = (TextView) mView.findViewById(R.id.tv_weight);
			backNumberTv = (TextView) mView.findViewById(R.id.tv_backnumber);
			contactDateTv = (TextView) mView.findViewById(R.id.tv_contactdate);
			beforeClubTv = (TextView) mView.findViewById(R.id.tv_before_club);
			positionTv = (TextView) mView.findViewById(R.id.tv_position);
			
			nationTv.setText(mNation);
			birthDateTv.setText(mBirthDate);
			heightTv.setText(mHeight);
			weightTv.setText(mWeight);
			backNumberTv.setText(mBackNumber);
			contactDateTv.setText(mContactDate);
			beforeClubTv.setText(mBeforeClub);
			positionTv.setText(mPosition);
			
			playerImageIv = (ImageView) mView.findViewById(R.id.iv_player_image);
			// 이름 비교 후 지정된 플레이어 이미지로 변경
			Drawable mDrawable = changePlayerImage(mPlayerName);
			if (mDrawable != null) {
				playerImageIv.setBackgroundDrawable(changePlayerImage(mPlayerName));
			} else {
				UrlImageViewHelper.setUrlDrawable(playerImageIv, mPlayerImage);
			}
		}
	}
	
	// 외부에서 asynctask call
	public void callAsyncTask(String link) {
		showProgress("", "Data loading...");
		new PlayerInfoTask().execute(link);
	}
	
	// 선수 이미지, 이름 저장
	public void setPlayerData(String playerImage, String playerName) {
		mPlayerImage = playerImage;
		mPlayerName = playerName;
	}
	
	// 선수별 이미지 변경
	public Drawable changePlayerImage(String playername) {
		Drawable playerImage = null;
		if (playername.contains("레미")) {
			playerImage = getResources().getDrawable(R.drawable.remy);
		} else if (playername.contains("쉬를레")) {
			playerImage = getResources().getDrawable(R.drawable.schurrle);
		} else if (playername.contains("드로그바")) {
			playerImage = getResources().getDrawable(R.drawable.drogba);
		} else if (playername.contains("코스타")) {
			playerImage = getResources().getDrawable(R.drawable.costa);
		} else if (playername.contains("파브레가스")) {
			playerImage = getResources().getDrawable(R.drawable.fabregas);
		} else if (playername.contains("하미레스")) {
			playerImage = getResources().getDrawable(R.drawable.ramires);
		} else if (playername.contains("아자르")) {
			playerImage = getResources().getDrawable(R.drawable.hazard);
		} else if (playername.contains("오스카")) {
			playerImage = getResources().getDrawable(R.drawable.oscar);
		} else if (playername.contains("미켈")) {
			playerImage = getResources().getDrawable(R.drawable.mikel);
		} else if (playername.contains("살라")) {
			playerImage = getResources().getDrawable(R.drawable.salah);
		} else if (playername.contains("힌켈")) {
			playerImage = getResources().getDrawable(R.drawable.ginkel);
		} else if (playername.contains("마티치")) {
			playerImage = getResources().getDrawable(R.drawable.matic);
		} else if (playername.contains("윌리안")) {
			playerImage = getResources().getDrawable(R.drawable.willian);
		} else if (playername.contains("아케")) {
			playerImage = getResources().getDrawable(R.drawable.ake);
		} else if (playername.contains("이바노비치")) {
			playerImage = getResources().getDrawable(R.drawable.ivanovic);
		} else if (playername.contains("루이스")) {
			playerImage = getResources().getDrawable(R.drawable.luis);
		} else if (playername.contains("조우마")) {
			playerImage = getResources().getDrawable(R.drawable.zouma);
		} else if (playername.contains("케이힐")) {
			playerImage = getResources().getDrawable(R.drawable.cahill);
		} else if (playername.contains("테리")) {
			playerImage = getResources().getDrawable(R.drawable.terry);
		} else if (playername.contains("아스필리쿠에타")) {
			playerImage = getResources().getDrawable(R.drawable.azpilicueta);
		} else if (playername.contains("크리스천슨")) {
			playerImage = getResources().getDrawable(R.drawable.christensen);
		} else if (playername.contains("체흐")) {
			playerImage = getResources().getDrawable(R.drawable.cech);
		} else if (playername.contains("쿠르투아")) {
			playerImage = getResources().getDrawable(R.drawable.courtois);
		} else if (playername.contains("슈워처")) {
			playerImage = getResources().getDrawable(R.drawable.schwarzer);
		} else {
			playerImage = null;
		}
		return playerImage;
	}
}
