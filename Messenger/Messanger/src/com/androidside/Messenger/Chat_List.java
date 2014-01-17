package com.androidside.Messenger;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class Chat_List extends ListActivity implements OnItemClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrings));
        getListView().setTextFilterEnabled(true);
        getListView().setOnItemClickListener(this);
    }
    
    private String[] mStrings = {
    		"Messi","Kaka","Drogba","Tevez","Ronaldo","Terry","Jisung","Roony","Lampard","Gerrard","Pique","Fabregas"
    };        
        // 사용자 어댑터를 사용함
//        setListAdapter(new SpeechListAdapter(this));

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		startActivity(new Intent(this, ChatRoom.class));		
	}
   
}
