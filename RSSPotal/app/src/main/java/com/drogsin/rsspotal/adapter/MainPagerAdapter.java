package com.drogsin.rsspotal.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;

import com.drogsin.rsspotal.R;
import com.drogsin.rsspotal.fragment.LiveScoreFragment1;
import com.drogsin.rsspotal.fragment.LiveScoreFragment2;
import com.drogsin.rsspotal.fragment.LiveScoreFragment3;
import com.drogsin.rsspotal.fragment.LiveScoreFragment4;
import com.drogsin.rsspotal.fragment.LiveScoreFragment5;
import com.drogsin.rsspotal.fragment.LiveScoreFragment6;
import com.drogsin.rsspotal.fragment.LiveScoreFragment7;
import com.drogsin.rsspotal.fragment.LiveScoreFragment8;
import com.drogsin.rsspotal.fragment.LiveScoreFragment9;
import com.drogsin.rsspotal.widget.ScrollTabHolder;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by jjm on 2015-09-23.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private final String TAG = "MainPagerAdapter";

    private Context context;
    private SparseArrayCompat<ScrollTabHolder> mScrollTabHolders;
    private ScrollTabHolder mListener;
    private final int TAB_COUNT = 9;

    // set livescore fragment
    private LiveScoreFragment1 mLiveScoreFragment1;
    private LiveScoreFragment2 mLiveScoreFragment2;
    private LiveScoreFragment3 mLiveScoreFragment3;
    private LiveScoreFragment4 mLiveScoreFragment4;
    private LiveScoreFragment5 mLiveScoreFragment5;
    private LiveScoreFragment6 mLiveScoreFragment6;
    private LiveScoreFragment7 mLiveScoreFragment7;
    private LiveScoreFragment8 mLiveScoreFragment8;
    private LiveScoreFragment9 mLiveScoreFragment9;

    public MainPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        mScrollTabHolders = new SparseArrayCompat<ScrollTabHolder>();
    }

    public void setTabHolderScrollingContent(ScrollTabHolder listener) {
        mListener = listener;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0 :
                return context.getString(R.string.str_primary);
            case 1 :
                return context.getString(R.string.str_entertain);
            case 2 :
                return context.getString(R.string.str_sports);
            case 3 :
                return context.getString(R.string.str_politics);
            case 4 :
                return context.getString(R.string.str_society);
            case 5 :
                return context.getString(R.string.str_economic);
            case 6 :
                return context.getString(R.string.str_foreign);
            case 7 :
                return context.getString(R.string.str_culture);
            case 8 :
                return context.getString(R.string.str_digital);
            default:
                return "";
        }
    }

    @Override
    public int getCount() {

        return TAB_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
//        return fragment;
        mLiveScoreFragment1 = new LiveScoreFragment1();
        mLiveScoreFragment2 = new LiveScoreFragment2();
        mLiveScoreFragment3 = new LiveScoreFragment3();
        mLiveScoreFragment4 = new LiveScoreFragment4();
        mLiveScoreFragment5 = new LiveScoreFragment5();
        mLiveScoreFragment6 = new LiveScoreFragment6();
        mLiveScoreFragment7 = new LiveScoreFragment7();
        mLiveScoreFragment8 = new LiveScoreFragment8();
        mLiveScoreFragment9 = new LiveScoreFragment9();
        switch (position) {
            case 0:
                mScrollTabHolders.put(position, mLiveScoreFragment1);
                if (mListener != null) {
                    mLiveScoreFragment1.setScrollTabHolder(mListener);
                }
                return mLiveScoreFragment1;
            case 1:
                mScrollTabHolders.put(position, mLiveScoreFragment2);
                if (mListener != null) {
                    mLiveScoreFragment2.setScrollTabHolder(mListener);
                }
                return mLiveScoreFragment2;
            case 2:
                mScrollTabHolders.put(position, mLiveScoreFragment3);
                if (mListener != null) {
                    mLiveScoreFragment3.setScrollTabHolder(mListener);
                }
                return mLiveScoreFragment3;
            case 3:
                mScrollTabHolders.put(position, mLiveScoreFragment4);
                if (mListener != null) {
                    mLiveScoreFragment4.setScrollTabHolder(mListener);
                }
                return mLiveScoreFragment4;
            case 4:
                mScrollTabHolders.put(position, mLiveScoreFragment5);
                if (mListener != null) {
                    mLiveScoreFragment5.setScrollTabHolder(mListener);
                }
                return mLiveScoreFragment5;
            case 5:
                mScrollTabHolders.put(position, mLiveScoreFragment6);
                if (mListener != null) {
                    mLiveScoreFragment6.setScrollTabHolder(mListener);
                }
                return mLiveScoreFragment6;
            case 6:
                mScrollTabHolders.put(position, mLiveScoreFragment7);
                if (mListener != null) {
                    mLiveScoreFragment7.setScrollTabHolder(mListener);
                }
                return mLiveScoreFragment7;
            case 7:
                mScrollTabHolders.put(position, mLiveScoreFragment8);
                if (mListener != null) {
                    mLiveScoreFragment8.setScrollTabHolder(mListener);
                }
                return mLiveScoreFragment8;
            case 8:
                mScrollTabHolders.put(position, mLiveScoreFragment9);
                if (mListener != null) {
                    mLiveScoreFragment9.setScrollTabHolder(mListener);
                }
                return mLiveScoreFragment9;
            default:
                mScrollTabHolders.put(position, mLiveScoreFragment1);
                if (mListener != null) {
                    mLiveScoreFragment1.setScrollTabHolder(mListener);
                }
                return mLiveScoreFragment1;
        }
    }

    public SparseArrayCompat<ScrollTabHolder> getScrollTabHolders() {
        return mScrollTabHolders;
    }
}
