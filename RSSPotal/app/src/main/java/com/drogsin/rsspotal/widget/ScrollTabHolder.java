package com.drogsin.rsspotal.widget;

import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;

public interface ScrollTabHolder {

	void adjustScroll(int scrollHeight);

	void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition);
}
