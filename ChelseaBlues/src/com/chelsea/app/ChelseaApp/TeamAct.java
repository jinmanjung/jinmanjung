package com.chelsea.app.ChelseaApp;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TeamAct extends Activity {

	ArrayList<MyItem> arItem;
	WebView wv = null;
	AlertDialog.Builder alert;
	String url = null;
	int version;
	private static final String TAG = TeamAct.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teamlistview);
		Log.d(TAG, "onCreate");
		version = android.os.Build.VERSION.SDK_INT;
		if(version >= 11){
			Log.d(TAG, "android.os.Build.VERSION.SDK_INT ="+android.os.Build.VERSION.SDK_INT);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, 
					WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
		}
		arItem = new ArrayList<MyItem>();
		MyItem mi;
		mi = new MyItem(R.drawable.cech, R.string.str_info_cech);arItem.add(mi);
		mi = new MyItem(R.drawable.iva, R.string.str_info_iva);arItem.add(mi);
		mi = new MyItem(R.drawable.cole, R.string.str_info_cole);arItem.add(mi);
		mi = new MyItem(R.drawable.luiz, R.string.str_info_ruiz);arItem.add(mi);
		mi = new MyItem(R.drawable.essien, R.string.str_info_essien);arItem.add(mi);
//		mi = new MyItem(R.drawable.romeu, "6 Oriol Romeu   [MF]");arItem.add(mi);
		mi = new MyItem(R.drawable.ramires, R.string.str_info_ramires);arItem.add(mi);
		mi = new MyItem(R.drawable.lampard, R.string.str_info_lampard);arItem.add(mi);
		mi = new MyItem(R.drawable.torres, R.string.str_info_torres);arItem.add(mi);
		mi = new MyItem(R.drawable.mata, R.string.str_info_mata);arItem.add(mi);
		mi = new MyItem(R.drawable.oscar, R.string.str_info_oscar);arItem.add(mi);
		mi = new MyItem(R.drawable.mikel, R.string.str_info_mikel);arItem.add(mi);
//		mi = new MyItem(R.drawable.moses, R.string.str_info_moses);arItem.add(mi);
		mi = new MyItem(R.drawable.schurrle, R.string.str_info_schurrle);arItem.add(mi);
		mi = new MyItem(R.drawable.bruyne, R.string.str_info_bruyne);arItem.add(mi);
		mi = new MyItem(R.drawable.ginkel, R.string.str_info_ginkel);arItem.add(mi);
		mi = new MyItem(R.drawable.hazard, R.string.str_info_hazard);arItem.add(mi);
		mi = new MyItem(R.drawable.rukaku, R.string.str_info_rukaku);arItem.add(mi);
		mi = new MyItem(R.drawable.ba, R.string.str_info_ba);arItem.add(mi);
		mi = new MyItem(R.drawable.marin, R.string.str_info_marin);arItem.add(mi);
		mi = new MyItem(R.drawable.willian, R.string.str_info_willian);arItem.add(mi);
		mi = new MyItem(R.drawable.schwarzer, R.string.str_info_schwarzer);arItem.add(mi);
		mi = new MyItem(R.drawable.cahill, R.string.str_info_cahill);arItem.add(mi);
		mi = new MyItem(R.drawable.terry, R.string.str_info_terry);arItem.add(mi);
		mi = new MyItem(R.drawable.tanco, R.string.str_info_tanco);arItem.add(mi);
		mi = new MyItem(R.drawable.etoo, R.string.str_info_etoo);arItem.add(mi);
		mi = new MyItem(R.drawable.kalas, R.string.str_info_kalas);arItem.add(mi);
		mi = new MyItem(R.drawable.bertrand, R.string.str_info_bertrand);arItem.add(mi);
		mi = new MyItem(R.drawable.hillario, R.string.str_info_hillario);arItem.add(mi);
		mi = new MyItem(R.drawable.blackman, R.string.str_info_blackman);arItem.add(mi);
		
		MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.teams, arItem);
		
		ListView ChelseaList;
		ChelseaList = (ListView)findViewById(R.id.list);
		ChelseaList.setAdapter(MyAdapter);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(wv != null){
			wv.destroy();
			wv.destroyDrawingCache();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	//리스트 뷰에 출력할 항목
	class MyItem{
		MyItem(int aIcon, int aName){
		Icon = aIcon;
		
		Name = aName;
		}
		int Icon;
		int Name;
	}
	
	public class MyListAdapter extends BaseAdapter {

		Context maincon;
		LayoutInflater Inflater;
		ArrayList<MyItem> arSrc;
		int layout;
		
		public MyListAdapter(Context context, int alayout, ArrayList<MyItem> aarSrc) {
			// TODO Auto-generated constructor stub
			maincon = context;
			Inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			arSrc = aarSrc;
			layout = alayout;
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return arSrc.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return arSrc.get(position).Name;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			final int pos = position;
			if(convertView == null){
				convertView = Inflater.inflate(layout, parent, false);
			}
			ImageView img = (ImageView)convertView.findViewById(R.id.img);
			img.setImageResource(arSrc.get(position).Icon);
			
			TextView txt = (TextView)convertView.findViewById(R.id.text);
			txt.setText(arSrc.get(pos).Name);
			
			Button btn = (Button)convertView.findViewById(R.id.btn);
			btn.setOnClickListener(new Button.OnClickListener(){

				public void onClick(View v) {
					// TODO Auto-generated method stub
					Uri uri = null;
					Intent web = null;
					
					switch(arSrc.get(pos).Icon){
					case R.drawable.cech:
						url = "http://www.youtube.com/watch?v=myljhwQjnoU";
						break;
					case R.drawable.iva:
						url = "http://www.youtube.com/watch?v=KJL4DLZlvDY";
						break;
					case R.drawable.cole:
						url = "http://www.youtube.com/watch?v=XXvifFZHecE";
						break;
					case R.drawable.luiz:
						url = "http://www.youtube.com/watch?v=JU0Dcg5iK6Q";
						break;
					case R.drawable.essien:
						url = "http://www.youtube.com/watch?v=ODxuMH0_M5M";
						break;
//					case R.drawable.romeu:
//						url = "http://www.youtube.com/watch?v=znDwChPEOAs";
//						break;
					case R.drawable.ramires:
						url = "http://www.youtube.com/watch?v=5DCi4PY0elU";;
						break;
					case R.drawable.lampard:
						url = "http://www.youtube.com/watch?v=lc4EMW6GuSU";
						break;
					case R.drawable.torres:
						url = "http://www.youtube.com/watch?v=uRWEE56tzqk";;
						break;
					case R.drawable.mata:
						url = "http://www.youtube.com/watch?v=sZnRP_AcBi4";
						break;
					/*	
					case R.drawable.drogba:
						uri = Uri.parse("http://www.youtube.com/watch?v=etKJm7i0K6I");
						web = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(web);
						break;
					*/
					case R.drawable.oscar:
						url = "http://www.youtube.com/watch?v=1dh9Ja7IHhI";
						break;
					case R.drawable.mikel:
						url = "http://www.youtube.com/watch?v=6T-7T2rza3k";
						break;	
					case R.drawable.moses:
						url = "http://www.youtube.com/watch?v=VFV1dNfam58";
						break;
					case R.drawable.schurrle:
						url = "http://www.youtube.com/watch?v=CGjUJsGyQ7Y";
						break;
					case R.drawable.bruyne:
						url = "http://www.youtube.com/watch?v=WaIJLpvcd_Q";
						break;
					case R.drawable.ginkel:
						url = "http://www.youtube.com/watch?v=P_Il6Gximnw";
						break;
					case R.drawable.hazard:
						url = "http://www.youtube.com/watch?v=6uviGrX8kOI";
						break;
//					case R.drawable.rukaku:
//						url = "http://www.youtube.com/watch?v=HinKKzOCGgU";
//						break;
//					case R.drawable.marin:
//						url = "http://www.youtube.com/watch?v=gvAJttqOB4Q";
//						break;
					case R.drawable.willian:
						url = "http://www.youtube.com/watch?v=exo5oEy1uMw";
						break;
					case R.drawable.schwarzer:
						url = "http://www.youtube.com/watch?v=ejsUFzlH5I4";
						break;
					case R.drawable.cahill:
						url = "http://www.youtube.com/watch?v=bGL_WqAyJpM";
						break;
					case R.drawable.terry:
						url = "http://www.youtube.com/watch?v=hUaa3a50bzg";
						break;
					/*	
					case R.drawable.sam:
						uri = Uri.parse("http://www.youtube.com/watch?v=a1T7NrglitM");
						web = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(web);
						break;	
					*/
					case R.drawable.tanco:
						url = "http://www.youtube.com/watch?v=DktXxapZjJ0";
						break;	
					case R.drawable.ba:
						url = "http://www.youtube.com/watch?v=WDnjG4HNQX4";
						break;
					case R.drawable.etoo:
						url = "http://www.youtube.com/watch?v=0sTFZh4CtLg";
						break;
					case R.drawable.kalas:
						url = "http://www.youtube.com/watch?v=f4GDPNIj-G8";
						break;
					case R.drawable.bertrand:
						url = "http://www.youtube.com/watch?v=z7WGyJfYrjs";
						break;
					case R.drawable.hillario:
						url = "http://www.youtube.com/watch?v=0j5ZWXV0vLg";
						break;		
					case R.drawable.blackman:
						url = "http://www.youtube.com/watch?v=d3kEdWdbnIQ";
						break;
					}
//					if(version > 10){
//						webViewInDiag(url);
//					}	
//					else {
						uri = Uri.parse(url);
						web = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(web);
//					}
				}		
			});
			
			return convertView;
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onKeyDown");
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
	
	private void webViewInDiag(String url) {
		// TODO Auto-generated method stub
		//create webview
		Log.d(TAG, "webViewInDiag , url = "+url );
		wv = new WebView(this);
		wv.loadUrl(url);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.getSettings().setPluginState(PluginState.ON);
		wv.getSettings().setPluginsEnabled(true);
		wv.setWebViewClient(new WebViewClient());
		wv.setWebChromeClient(new WebChromeClient());
		
		
		//create alert dialog 
		alert = new AlertDialog.Builder(this);
		alert.setView(wv);

		alert.setCancelable(true);
		alert.setOnKeyListener(new OnKeyListener() {
			
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				Log.d(TAG, "setOnKeyListener");
				if(keyCode == event.KEYCODE_BACK){
					Log.d(TAG, "KEYCODE_BACK");
				}
				return false;
			}
		});
		alert.setOnCancelListener(new OnCancelListener() {
			
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				Log.d(TAG, "setOnCancelListener");
				wv.destroy();
				wv.destroyDrawingCache();
			}
		});
		alert.show();
	}
}
