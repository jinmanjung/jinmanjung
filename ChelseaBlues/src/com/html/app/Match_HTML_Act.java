package com.html.app;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.chelsea.app.ChelseaApp.R;
import com.html.net.HTMLParse;

public class Match_HTML_Act extends Activity implements OnItemClickListener {

	private static final String TAG = News_HTML_Act.class.getSimpleName();
	private ArrayList<HashMap<String, Object>> data;
	private ListView mMatchList;
	private SimpleAdapter sa;
	private HTMLParse hp;
	private String type = null;

	private final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			listUpdate();
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.matches);

		data = new ArrayList<HashMap<String, Object>>();
		mMatchList = (ListView) findViewById(R.id.listView1);

		hp = new HTMLParse(Match_HTML_Act.this, handler, data);

		sa = new SimpleAdapter(Match_HTML_Act.this, data, R.layout.match_list,
			new String[] { "test1", "test2", "test4", "test5", "test6", "test3" },
			new int[] { R.id.tv_day, R.id.tv_match, R.id.tv_vs, R.id.tv_winer, R.id.tv_result }
		);

		mMatchList.setDivider(new ColorDrawable(Color.parseColor("#27519A")));
		mMatchList.setDividerHeight(3);
		mMatchList.setAdapter(sa);
		mMatchList.setOnItemClickListener(this);
		type = "match";
		hp.open(type);
	}

	// 업데이트하기
	private void listUpdate() {
		sa.notifyDataSetChanged();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
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

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		boolean started = false;
		Log.d(TAG, "onItemClick");
		hp.setMatchIndex(position);

		ArrayList<HashMap<String, String>> saveData = hp.getSaveData();
		for (int i = 0; i < saveData.size(); i++) {
			if (saveData.get(i).get("index").equals(position + "")) {
				startMatchDetailActivity(saveData.get(i));
				started = true;
			}
		}
		if (!started) {
			hp.open("matchDetail");
		}
	}

	public void startMatchDetailActivity(HashMap<String, String> data) {
		Intent i = new Intent(this, MatchDetail_HTML_Act.class);
		i.putExtra("summary", data.get("summary"));
		startActivity(i);
	}
}