package com.html.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chelsea.app.ChelseaApp.R;

public class NewsDetail_HTML_Act extends Activity{
	private static final String TAG = News_HTML_Act.class.getSimpleName();
	private static final String origURL = "http://korea.chelseafc.com";
	private static final int TITLE_IMAGE = 1;
	private static final int CONTENTS_IMAGE = 2;

	private TextView tvTitle, tvDate, tvContents;
	private ImageView ivIMG;
	
	private ArrayList<Drawable> mImageList;
	private int mListCount;
	private String mContents;
	private String mMainImageUrl;
	
    private Handler postHandler = new Handler(){
    	@Override
        public void handleMessage(Message msg){
        	if(msg.what == TITLE_IMAGE) {	// resize after main image download
        		Drawable d = (Drawable) msg.obj;
    			int imageResizeHeight = resizeImage(d);
    			ivIMG.setImageDrawable(d);
    			ivIMG.setScaleType(ImageView.ScaleType.CENTER_CROP);
    			ivIMG.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, imageResizeHeight));
        	} else if(msg.what == CONTENTS_IMAGE) {	// display after contents image download
        		Spanned sp = (Spanned) msg.obj;
        		tvContents.setText(sp);
        		tvContents.setMovementMethod(LinkMovementMethod.getInstance());
        	}
        }
    };
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.detail);
		Log.i(TAG, "onCreate");
		tvTitle = (TextView)findViewById(R.id.newsTitle);
		tvDate = (TextView)findViewById(R.id.newsDate);
		tvContents = (TextView)findViewById(R.id.newsDetail);
		ivIMG = (ImageView)findViewById(R.id.newsImage);
		
		mImageList = new ArrayList<Drawable>();
		mListCount = 0;
		mContents = null;
		mMainImageUrl = null;
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, "onStart");
		Intent intent = getIntent();
		
		mMainImageUrl = intent.getStringExtra("img");

		new Thread() {
			public void run() {		// main image download
				try {
					Message msg = new Message();
					Drawable d = null;
					
					if(isExistImageFile(null)) {
						d = loadImageFile(null);
					} else {
						InputStream is = (InputStream) new URL(mMainImageUrl).getContent();
						d = Drawable.createFromStream(is, "src name");
						saveImageToFile(d, null);
					}

					msg.what = TITLE_IMAGE;
					msg.obj = d;
					postHandler.sendMessage(msg);
		
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();

		final ImageGetter imageGetter = new ImageGetter() {	// ImageGetter for contents image download & resize

			public Drawable getDrawable(String source) {
				// TODO Auto-generated method stub
				Drawable d = null;
				try {
				//	Point OutSize = new Point();
				//	getWindowManager().getDefaultDisplay().getSize(OutSize);

					if(isExistImageFile(source)) {
						d = loadImageFile(source);
					} else {
						d = Drawable.createFromStream((InputStream) new URL(origURL + source).getContent(), "src name");
						saveImageToFile(d, source);
					}
					int imageResizeHeight = resizeImage(d);
				//	d.setBounds(0, 0, OutSize.x, imageResizeHeight);	// resize the image
					d.setBounds(0, 0, getWindowManager().getDefaultDisplay().getWidth(), imageResizeHeight);	// resize the image
					mImageList.add(mListCount++, d);					// save content image for rotation
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return d;
			}
		};

		tvTitle.setText(intent.getStringExtra("title"));
		tvDate.setText(intent.getStringExtra("date"));
		mContents = intent.getStringExtra("contents");
		
		new Thread() {
			public void run() {		// contents image download in imageGetter
				Message msg = new Message();
				Spanned sp = Html.fromHtml(mContents, imageGetter, null);
				
				msg.what = CONTENTS_IMAGE;
				msg.obj = sp;
				postHandler.sendMessage(msg);
			}
		}.start();
	}
	
	public int resizeImage(Drawable d) {
		
	//	Point OutSize = new Point();
	//	getWindowManager().getDefaultDisplay().getSize(OutSize);
		
	//	int deviceWidth = OutSize.x;
	//	int deviceHeight = OutSize.y;
		int deviceWidth = getWindowManager().getDefaultDisplay().getWidth();
		int deviceHeight = getWindowManager().getDefaultDisplay().getHeight();

		float rate = 0;
		int height = 0;
		
		rate = (float)deviceWidth / (float)d.getIntrinsicWidth();
		height = (int)(d.getIntrinsicHeight() * rate);
		
		Log.i(TAG, "deviceWidth = " + deviceWidth + " deviceHeight = " + deviceHeight);
		Log.i(TAG, "getIntrinsicWidth = " + d.getIntrinsicWidth());
		Log.i(TAG, "getIntrinsicHeight = " + d.getIntrinsicHeight());
		Log.i(TAG, "rate = " + rate);
		Log.i(TAG, "height = " + height);
		
		return height;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		Log.i(TAG, "onConfigurationChanged() called");
		
		ImageGetter imageReGetter = new ImageGetter() {	// ImageGetter for rotation (use saved image)
			
			public Drawable getDrawable(String source) {
				// TODO Auto-generated method stub
				Drawable d = null;
				
				if(mListCount < mImageList.size()) {
					d = mImageList.get(mListCount);
				}
				
				if(d != null) {
				//	Point OutSize = new Point();
				//	getWindowManager().getDefaultDisplay().getSize(OutSize);
					
					int imageResizeHeight = resizeImage(d);
				//	d.setBounds(0, 0, OutSize.x, imageResizeHeight);
					d.setBounds(0, 0, getWindowManager().getDefaultDisplay().getWidth(), imageResizeHeight);
					mListCount++;
				}
				return d;
			}
		};
		Drawable d = ivIMG.getDrawable();
		if(d != null) {
			int imageResizeHeight = resizeImage(ivIMG.getDrawable());	// main image resize for rotation
			ivIMG.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, imageResizeHeight));
		}
		
		if(mListCount != 0) {		// contents image resize for rotation
			mListCount = 0;
			tvContents.setText(Html.fromHtml(mContents, imageReGetter, null));
		}
	}
	
	public void saveImageToFile(Drawable d, String url) {
		FileOutputStream fos;
		
		Bitmap bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
		d.draw(canvas);

		try {
			fos = new FileOutputStream(getImageFileName(url));

			if(fos != null) {
				bitmap.compress(Bitmap.CompressFormat.PNG , 100, fos);
				fos.flush();
				fos.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isExistImageFile(String url) {
		File file = new File(getImageFileName(url));
		if(file.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Drawable loadImageFile(String url) {
		Bitmap bitmap = BitmapFactory.decodeFile(getImageFileName(url));
		Drawable drawable = (Drawable)(new BitmapDrawable(bitmap));
		
		return drawable;
	}
	
	public String getImageFileName(String url) {
		int index = 0;
		String fileName = null;
		String filePath = null;
		
		if(url == null) {	// main image
			index = mMainImageUrl.indexOf("news/");
			fileName = mMainImageUrl.substring(index+4);
		} else {
			index = url.indexOf("images/");
			fileName = url.substring(index+6);
		}
		filePath = getApplicationContext().getFilesDir().getPath().toString() + fileName;
		Log.i(TAG, "getImageFileName() filePath = " + filePath);

		return filePath;
	}
	
}
