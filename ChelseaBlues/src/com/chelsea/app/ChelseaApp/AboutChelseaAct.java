package com.chelsea.app.ChelseaApp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.html.app.News_HTML_Act;

public class AboutChelseaAct extends Activity{
	
	private static final String TAG = News_HTML_Act.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chelsea);
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == event.KEYCODE_BACK){
			Log.d(TAG, "KEYCODE_BACK");
			AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

			dialogBuilder.setTitle(R.string.str_quit);
			dialogBuilder.setMessage(R.string.str_quitquestion);
			dialogBuilder.setPositiveButton(R.string.str_cancel, null);
			dialogBuilder.setNegativeButton(R.string.str_confirm, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			dialogBuilder.show(); 
		}
		return super.onKeyDown(keyCode, event);
	}
}
