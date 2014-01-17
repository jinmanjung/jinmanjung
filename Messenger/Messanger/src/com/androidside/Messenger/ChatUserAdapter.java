package com.androidside.Messenger;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ChatUserAdapter extends ArrayAdapter<ChatUserItem>{

	private ArrayList<ChatUserItem> items;
	
	public ChatUserAdapter(Context context, int textViewResourceId1,int textViewResourceId2,
			ArrayList<ChatUserItem> items) {
		super(context, textViewResourceId1, items);
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View v = convertView;			

		Log.i("Messanger", ""+position);
		switch(items.get(position).getType())
		{
		case 0:
			LayoutInflater vi_you = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi_you.inflate(R.layout.chat_room_item_you, null);
			break;
		case 1:
			LayoutInflater vi_me = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi_me.inflate(R.layout.chat_room_item_me, null);
			break;
		}

		ChatUserItem userItem = items.get(position);

		if ( userItem != null )
		{
			TextView msg = (TextView)v.findViewById(R.id.message);
			msg.setText(userItem.getMsg());
			
			TextView time = (TextView)v.findViewById(R.id.time);
			Calendar c = Calendar.getInstance();
			Log.i("Messanger", ""+time.getText());

			if(time.getText().equals("12:30"))
			{
				time.setText((c.get(Calendar.AM_PM) == 1?"오후 ":"오전 ") +
						(c.get(Calendar.HOUR)) + (":") + (c.get(Calendar.MINUTE)));			}
		}
						
		return v;
	}
}
