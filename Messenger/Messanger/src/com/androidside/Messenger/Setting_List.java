package com.androidside.Messenger;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class Setting_List extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings));
        getListView().setTextFilterEnabled(true);
    }
    
    private String[] mStrings = {
    		"공지사항","도움말","버전정보","내프로필","선물함","친구관리"
    };
}