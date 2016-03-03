package com.drogsin.rsspotal.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.AbsListView;

import com.drogsin.rsspotal.widget.ScrollTabHolder;

public abstract class ScrollTabHolderFragment extends FragmentBase implements ScrollTabHolder {

	protected Context mContext;
	protected ScrollTabHolder mScrollTabHolder;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		Activity activity = this.getActivity();
	}

	public void setScrollTabHolder(ScrollTabHolder scrollTabHolder) {
		mScrollTabHolder = scrollTabHolder;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition) {
		// nothing
	}

}