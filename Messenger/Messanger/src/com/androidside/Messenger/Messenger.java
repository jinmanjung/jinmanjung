package com.androidside.Messenger;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class Messenger extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//        setContentView(R.layout.main);
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
        
        TabHost tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.tabs, tabHost.getTabContentView(),true);
        
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("친구").setContent(new Intent(this, Friends_List.class)));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("채팅").setContent(new Intent(this, Chat_List.class)));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("설정").setContent(new Intent(this, Setting_List.class)));
    }
}