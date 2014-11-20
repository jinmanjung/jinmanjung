package com.chelsea.app.chelseablues.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.google.analytics.tracking.android.Log;

public class Utils {
	
	// url -> bitmap
	public static Bitmap urlToBitmap(String url) {
		Bitmap bitmap = null;
		
		try {
			InputStream is = (InputStream) new URL(url).openStream();
			bitmap = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bitmap;
	}
	
	// url -> drawable
	public static Drawable urlToDrawable(String url) {
		Bitmap bitmap = null;
		Drawable drawable = null;
		
		try {
			InputStream is = (InputStream) new URL(url).openStream();
			bitmap = BitmapFactory.decodeStream(is);
			drawable = (Drawable)(new BitmapDrawable(bitmap));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return drawable;
	}
	
	
	/**
	 * 시스템 언어 가져오기
	 * @return
	 */
	public static String getSystemLocale(Context context) {
		Locale systemLocale = context.getResources().getConfiguration().locale;
		String strLanguage = systemLocale.getLanguage();
		return strLanguage;
	}
	
	/**
	 *  Application 버전 (AndroidManifest의 값)
	 * @param ctx
	 * @return
	 */
	public static String getAppVersion(Context ctx) {
		String versionName = ""; 
				
		try {
			versionName = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionName;
		}
		catch (Exception e) {
//			Log.d("Utils", "Getting versionName from Manifest failed: " + e.getMessage());
		}
		
		return versionName;
	}
	
	/**
     * Process가 실행중인지 여부 확인.
     * @param context, packageName
     * @return true/false
     */
    public static boolean isRunningProcess(Context context, String packageName) {
 
        boolean isRunning = false;
 
        ActivityManager actMng = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);                      
 
        List<RunningAppProcessInfo> list = actMng.getRunningAppProcesses();     
 
        for(RunningAppProcessInfo rap : list)                           
        {                                
            if(rap.processName.equals(packageName))                              
            {                                   
                isRunning = true;     
                break;
            }                         
        }
 
        return isRunning;
    }
}
