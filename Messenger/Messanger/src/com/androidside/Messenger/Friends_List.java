package com.androidside.Messenger;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Friends_List extends Activity {
    private static final String[] projection = new String[]{
    	ContactsContract.Contacts._ID,
    	ContactsContract.Contacts.DISPLAY_NAME,
    	ContactsContract.Contacts.HAS_PHONE_NUMBER,
    	ContactsContract.Contacts.LOOKUP_KEY
    };
    
    private int mHasPhoneColumnIndex;
    private int mIdColumnIndex;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_list);
        Cursor c = managedQuery(ContactsContract.Contacts.CONTENT_URI, projection, null, null, null);
        mIdColumnIndex = c.getColumnIndex(ContactsContract.Contacts._ID);
        mHasPhoneColumnIndex = c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);

        ListView list;
        list = (ListView)findViewById(R.id.list);
        ListAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, c, 
        		new String[] {ContactsContract.Contacts.DISPLAY_NAME},
        		new int[] {android.R.id.text1});
        list.setAdapter(adapter);
        list.setOnItemClickListener(mItemClickListener);
    }
    
    AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {

		public void onItemClick(AdapterView adapter, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			String title;
			if(position >= 0){
				final Cursor c = (Cursor)adapter.getItemAtPosition(position);
				final long contactId = c.getLong(mIdColumnIndex);
				final Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
					new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
					null,
					null);
				phones.moveToFirst();
				try{
					title = phones.getString(0);
					new AlertDialog.Builder(Friends_List.this)
					.setTitle(title)
					.setIcon(R.drawable.img_profile_default)
					.setPositiveButton("1:1 Chatting", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							startActivity(new Intent(Friends_List.this, ChatRoom.class));
						}
					})
					.show();
				}finally{
					phones.close();
				}
			}
			else{
				new AlertDialog.Builder(Friends_List.this)
				.setTitle("Nothing")
				.show();
			}
		}
	};
}