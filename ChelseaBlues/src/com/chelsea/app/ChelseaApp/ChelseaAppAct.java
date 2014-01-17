package com.chelsea.app.ChelseaApp;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;

import com.html.app.Match_HTML_Act;
import com.html.app.News_HTML_Act;

public class ChelseaAppAct extends TabActivity implements OnTabChangeListener {
    /** Called when the activity is first created. */
	TabHost mTabHost = null;
	private static final String TAG = ChelseaAppAct.class.getSimpleName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
      
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mTabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.taps, mTabHost.getTabContentView(),true);
        //mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("CHELSEA").setContent(new Intent(this, AboutChelseaAct.class)));
        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("TEAM").setContent(new Intent(this, TeamAct.class)));
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("MATCHES").setContent(new Intent(this, Match_HTML_Act.class)));
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("NEWS").setContent(new Intent(this, News_HTML_Act.class)));
        mTabHost.addTab(mTabHost.newTabSpec("tab4").setIndicator("LEGEND").setContent(new Intent(this, LegendAct.class)));
        
        //tab color set
        mTabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#27519A"));
        mTabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#27519A"));
        mTabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#27519A"));
        mTabHost.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor("#27519A"));
        //mTabHost.getTabWidget().getChildAt(4).setBackgroundColor(Color.parseColor("#27519A"));
        
        //리스너 등록
        mTabHost.setOnTabChangedListener(this);
        
        //tab 높이 조절
        TabWidget tw = mTabHost.getTabWidget();
        for(int a = 0; a<tw.getChildCount(); a++){
        	View v = tw.getChildAt(a);
        	v.getLayoutParams().height = this.getWindowManager().getDefaultDisplay().getWidth()/10;
        }
        mTabHost.setCurrentTab(0);
    }
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		Log.d(TAG, "onConfigurationChanged");
	}
}