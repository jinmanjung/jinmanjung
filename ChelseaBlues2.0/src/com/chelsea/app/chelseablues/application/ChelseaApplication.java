package com.chelsea.app.chelseablues.application;

import java.util.ArrayList;

import android.app.Application;
import android.content.Context;
import android.provider.MediaStore.Audio.Playlists;
import android.util.Log;

import com.chelsea.app.chelseablues.activity.TeamActivity;
import com.chelsea.app.chelseablues.model.Record;
import com.chelsea.app.chelseablues.util.HTMLUtil;
import com.chelsea.app.chelseablues.util.PrefUtil;

public class ChelseaApplication extends Application {

	private String type = "";
	/**
	 * URL
	 */
	public static final String MAIN_URL = "http://korea.chelseafc.com";
	public static final String NEWS_URL = "http://korea.chelseafc.com/news";
	public static final String MATCH_URL = "http://korea.chelseafc.com/facts/fixture";
	public static final String TEAM_URL = "http://korea.chelseafc.com/player";
	public static final String DEFAULT_PLAYER_URL = "http://korea.chelseafc.com//player/8";
	public static final String LEAGUE_TABLE_URL = "http://korea.chelseafc.com/facts/leaguetable";
	public static final String MATCH_DETAIL_URL = "http://korea.chelseafc.com/facts/match/report/198";

	@Override
	public void onCreate() {
		super.onCreate();
		
	}
	
	public ArrayList<Record> getMainPage(Context context) {
		type = "mainPage";
		return requestServerProc(context, ChelseaApplication.MAIN_URL, type);
	}
	
	public ArrayList<Record> getLatestTactics(Context context) {
		type = "latestTactics";
		return requestServerProc(context, PrefUtil.get(context, "latestMatchLink", ""), type);
	}

	public ArrayList<Record> getNewsInfo(Context context) {
		type = "news";
		return requestServerProc(context, ChelseaApplication.NEWS_URL, type);
	}
	
	public ArrayList<Record> getNewsDetailInfo(Context context, String newslink) {
		type = "getNewsDetailInfo";
		return requestServerProc(context, newslink, type);
	}

	public ArrayList<Record> getTeamInfo(Context context) {
		type = "teamInfo";
		return requestServerProc(context, ChelseaApplication.TEAM_URL,
				type);
	}
	
	public ArrayList<Record> getPlayerInfo(Context context, String url) {
		type = "playerInfo";
			return requestServerProc(context, url,
					type);
	}
	
	public ArrayList<Record> getLeagueTable(Context context) {
		type = "leaguetable";
		return requestServerProc(context, ChelseaApplication.LEAGUE_TABLE_URL,
				type);
	}
	
	public ArrayList<Record> getMatchInfo(Context context) {
		type = "matchInfo";
		return requestServerProc(context, ChelseaApplication.MATCH_URL,
				type);
	}
	
	public ArrayList<Record> getMatchDetail(Context context) {
		type = "matchDetail";
		return requestServerProc(context, ChelseaApplication.MATCH_DETAIL_URL,
				type);
	}
	
	public ArrayList<Record> getMatchSummary(Context context) {
		type = "matchSummary";
		return requestServerProc(context, ChelseaApplication.MATCH_DETAIL_URL,
				type);
	}
	
	public ArrayList<Record> getMatchGallery(Context context, String matchUrl) {
		type = "matchGallery";
		return requestServerProc(context, matchUrl,
				type);
	}
	
	public ArrayList<Record> requestServerProc(Context context, String url,
			String type) {
		return HTMLUtil.HttpRequest(context, url, type);
	}
}
