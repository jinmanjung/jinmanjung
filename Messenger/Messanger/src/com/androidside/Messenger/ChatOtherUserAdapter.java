package com.androidside.Messenger;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ChatOtherUserAdapter extends ArrayAdapter<ChatOtherUserItem>{

	private ArrayList<ChatOtherUserItem> items;

	public ChatOtherUserAdapter(Context context, int textViewResourceId,
			ArrayList<ChatOtherUserItem> items) {
		super(context, textViewResourceId, items);
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		return super.getView(position, convertView, parent);
		
		View v = convertView;			

		if ( v == null ) {
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.chat_room_item_you, null);
		}
		// 현재의 position을 가지고 item을 가져온다.
		ChatOtherUserItem userItem = items.get(position);

		if ( userItem != null )
		{
			TextView msg = (TextView)v.findViewById(R.id.message);
			msg.setText(userItem.getMsg());
			
			TextView time = (TextView)v.findViewById(R.id.time);
			Calendar c = Calendar.getInstance();
			time.setText((c.get(Calendar.AM_PM) == 1?"오후 ":"오전 ") +
					(c.get(Calendar.HOUR)) + (":") + (c.get(Calendar.MINUTE)));
		}
						
		return v;		
	}
}
