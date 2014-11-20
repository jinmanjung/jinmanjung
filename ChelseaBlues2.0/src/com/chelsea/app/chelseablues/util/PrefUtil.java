package com.chelsea.app.chelseablues.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.chelsea.app.chelseablues.model.Record.Result;

public class PrefUtil {

	private static SharedPreferences pref;
	
	private static synchronized void initialize(Context context){
		if(pref != null)
			return;

		pref = context.getSharedPreferences("com.chelsea.app.chelseablues", Context.MODE_PRIVATE);
	}
	
	public static SharedPreferences getSharedPreferences(){
		return pref;
	}
	
	
	public static synchronized String get(Context context, String key, String defValue){
		initialize(context);
		return pref.getString(key, defValue);
	}
	public static synchronized int get(Context context, String key, int defValue){
		initialize(context);
		return pref.getInt(key, defValue);
	}
	public static synchronized long get(Context context, String key, long defValue){
		initialize(context);
		return pref.getLong(key, defValue);
	}
	public static synchronized boolean get(Context context, String key, boolean defValue){
		initialize(context);
		return pref.getBoolean(key, defValue);
	}


	public static synchronized void set(Context context, String key, String value){
		initialize(context);
		SharedPreferences.Editor editor = pref.edit();
		
		editor.putString(key, value);
		
		editor.commit();
	}
	
	
	public static synchronized void set(Context context, String key, boolean value){
		initialize(context);
		SharedPreferences.Editor editor = pref.edit();
		
		editor.putBoolean(key, value);
		
		editor.commit();
	}
	
	public static void set(Context context, String key, int value) {
		initialize(context);
		SharedPreferences.Editor editor = pref.edit();
		
		editor.putInt(key, value);
		
		editor.commit();
	}

	public static void set(Context context, String key, long value) {
		initialize(context);
		SharedPreferences.Editor editor = pref.edit();
		
		editor.putLong(key, value);
		
		editor.commit();
	}
}
