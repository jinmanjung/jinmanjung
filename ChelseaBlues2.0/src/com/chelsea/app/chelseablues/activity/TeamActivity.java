package com.chelsea.app.chelseablues.activity;

import java.util.ArrayList;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Window;

import com.chelsea.app.chelseablues.R;
import com.chelsea.app.chelseablues.fragment.PlayerFragment;
import com.chelsea.app.chelseablues.fragment.TeamFragment;
import com.google.analytics.tracking.android.EasyTracker;

public class TeamActivity extends ActivityBase implements OnPageChangeListener, TabListener, TeamFragment.CustomOnclickListener {
	private final String TAG = "TeamActivity";
	private TeamFragment mTeamFragment;
	private PlayerFragment mPlayerFragment;
	private int currentItem;
	private static ViewPager mPager;
	private static ArrayList<Fragment> mItems;
	public static PagerAdapter mPagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// set title bar
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_team);
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
		setController();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	/**
	 * Activity using Google Analytics and EasyTracker.
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}
	
	private void setController() {
		/*
		 * Main Pager Items
		 */
//		mTeamFomationFragment = new TeamLatestTacticsFragment();
		mTeamFragment = new TeamFragment();
		mPlayerFragment = new PlayerFragment();
		/*
		 * Main Pager and Underline Page Indicator
		 */
		mPagerAdapter = new TeamPagerAdapter(getSupportFragmentManager());
		
		currentItem = 0;
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mPagerAdapter);
		mPager.setCurrentItem(currentItem);
		mPager.setOnPageChangeListener(this);
		mPager.setOffscreenPageLimit(3);
		
//		UnderlinePageIndicator indicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
//		indicator.setViewPager(mPager);
//		indicator.setOnPageChangeListener(MainActivity.this);
//		indicator.setFades(false);
	}
	
	
	/*
	 * PagerAdapter (런처)
	 */
	private class TeamPagerAdapter extends FragmentPagerAdapter {
		public TeamPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
			mItems = new ArrayList<Fragment>();
//			mItems.add(mTeamFomationFragment);
//			mItems.add(mTeamFragment);
		}
		
		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			if (position == 0) {
//				return mTeamFomationFragment;
				return mTeamFragment;
			} else if (position == 1) {
				return mPlayerFragment;
//			} else if (position == 2){
//				return mPlayerFragment;
			} else {
				return mPlayerFragment;
			}
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
	}

	public void moveToPage(int index) {
		// TODO Auto-generated method stub
		if (mPagerAdapter != null) {
			mPagerAdapter.notifyDataSetChanged();
		}
		mPager.setCurrentItem(index);
	}

	public void addItem(int position) {
		mItems.add(mPlayerFragment);
	}
	
	// CustomOnClickListener의 구현
	public void onClicked(String link, String playerImage, String playerName) {
		// TODO Auto-generated method stub
		mPlayerFragment.setPlayerData(playerImage, playerName);
		mPlayerFragment.callAsyncTask(link);
	}
}
