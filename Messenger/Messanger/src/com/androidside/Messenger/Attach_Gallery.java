package com.androidside.Messenger;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class Attach_Gallery extends Activity {
	@Override
	   public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.gallery1);
	       Gallery attach_Gallery = (Gallery) findViewById(R.id.gallery1);
	       Button btn = (Button) findViewById(R.id.att_btn);
	       btn.setOnClickListener(mAttachListener);
	       Cursor c = getContentResolver().query(Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null); //Image데이터 쿼리
	       ImageCursorAdapter adapter = new ImageCursorAdapter(this, c);
	       attach_Gallery.setAdapter(adapter);    
	   }
	View.OnClickListener mAttachListener = new OnClickListener(){

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(getBaseContext(), "첨부", Toast.LENGTH_SHORT).show();
		}
	};
	  
   class ImageCursorAdapter extends CursorAdapter {
    public ImageCursorAdapter(Context context, Cursor c) {
     super(context, c);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
     ImageView img = (ImageView)view;
    
     long id = cursor.getLong(cursor.getColumnIndexOrThrow(Images.Media._ID)); 
     Uri uri = ContentUris.withAppendedId(Images.Media.EXTERNAL_CONTENT_URI, id); //개별 이미지에 대한 URI 생성
 

     try {
         Bitmap bm = Images.Media.getBitmap(getContentResolver(), uri); //Bitmap 로드
         img.setImageBitmap(bm);
     } catch(Exception e) {}
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
     ImageView v = new ImageView(context);
	 v.setLayoutParams(new Gallery.LayoutParams(200, 200));
     v.setScaleType(ImageView.ScaleType.FIT_CENTER);
     return v;
    
    }
   }
	}