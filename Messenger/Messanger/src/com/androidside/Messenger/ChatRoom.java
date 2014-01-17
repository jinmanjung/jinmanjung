package com.androidside.Messenger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class ChatRoom extends Activity {
    protected static final int INTENT_ATTACH_IMAGE = 0;
	protected static final Intent Gallery1 = null;
	private final int requestCodeImage = 1004;
	private final int requestCodeVideo = 1005;
	private final int requestCodeAudio = 1006;
	
	public MessangerSocket mSock = new MessangerSocket(this);
	public ArrayList<ChatUserItem> list;
	public ChatUserAdapter adapter;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room_main);
        
        final EditText et = (EditText) findViewById(R.id.input_box); 
        Button send_btn = (Button) findViewById(R.id.send); 
        Button attach_btn = (Button) findViewById(R.id.attach);
        if(!mSock.socketStart())
        {
        	Toast.makeText(this, "Connection failed", Toast.LENGTH_SHORT).show();
        	this.finish();
        }
        
        list = new ArrayList<ChatUserItem>();
        adapter = new ChatUserAdapter(this, R.layout.chat_room_item_you, R.layout.chat_room_item_me, list);

        // 리스트뷰에 어댑터 연결
        final ListView listView = (ListView)findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        
        send_btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (et.getText().toString() != null 
                        && !et.getText().toString().equals("")) {
                    String et_msg = et.getText().toString();
                    mSock.sendMsg(et_msg);
                    et.setText("");
                    ChatUserItem temp;
                    
                    temp = new ChatUserItem(et_msg, 1);
                    list.add(temp);
                    adapter.notifyDataSetChanged();
                } 
            } 
        });
        
        attach_btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final CharSequence[] FileType = {"Image", "Video", "Audio"};  
				AlertDialog.Builder alt_bld = new AlertDialog.Builder(ChatRoom.this);
				alt_bld.setTitle("Select a File Type");  
				alt_bld.setSingleChoiceItems(FileType, -1, new DialogInterface.OnClickListener() {  
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
					public void onClick(DialogInterface dialog, int item) {  
						switch(item){
						case 0:	//image
							Toast.makeText(getApplicationContext(), FileType[item], Toast.LENGTH_SHORT).show();														
							intent.setType("image/*");
							startActivityForResult(intent, requestCodeImage);
							break;
						case 1:	//Video
							Toast.makeText(getApplicationContext(), FileType[item], Toast.LENGTH_SHORT).show();														
							intent.setType("video/*");
							startActivityForResult(intent, requestCodeVideo);
							break;
						case 2:	//Audio
							Toast.makeText(getApplicationContext(), FileType[item], Toast.LENGTH_SHORT).show();														
							intent.setType("audio/*");
							startActivityForResult(intent, requestCodeAudio);
							break;	
						}  
					dialog.cancel();  
					}  
				});  
				AlertDialog alert = alt_bld.create();  
				alert.show();  
			} 	
        });
    }

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.d("DAY", "onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.d("DAY", "onDestroy");
		super.onDestroy();
			mSock.socketStop(); 
	}
    
	protected void onActivityResult(int requestCode, int resultCode, Intent data){

		super.onActivityResult(requestCode, resultCode, data);

		ImageView iv = (ImageView) findViewById(R.id.resultImage);
		Log.d("DAY", "RequestCode:  " + requestCode + "   ResultCode:  "+ resultCode);
		switch(requestCode){
		case requestCodeImage:
			if (resultCode == RESULT_OK) {
				Uri imageUri = data.getData();
				long id = -1;
	
				String[] projection = {MediaStore.Images.Thumbnails._ID};        
				Cursor imageCursor = managedQuery(imageUri, projection, null, null, null);
	
				if(imageCursor != null && imageCursor.moveToNext()){
				        int imageIDCol = imageCursor.getColumnIndex(MediaStore.Images.Thumbnails._ID);
				        id = imageCursor.getLong(imageIDCol);
				}
				try{
					BitmapFactory.Options options=new BitmapFactory.Options();
					options.inSampleSize = 1;
					Bitmap curThumb = MediaStore.Images.Thumbnails.getThumbnail(getContentResolver(), id, MediaStore.Images.Thumbnails.MICRO_KIND, options);//비트맵에 이미지가 저장된다.
					iv.setImageBitmap(curThumb);
				}catch(Exception e){
				    e.printStackTrace();
				}
				
			}		
			else{
				Toast.makeText(getApplicationContext(), "Image File 가져오기 실패",Toast.LENGTH_SHORT).show();
			}
			break;
		case requestCodeVideo:
			if (resultCode == RESULT_OK){
				Uri videoUri = data.getData();
				long id = -1;
	
				String[] projection = {MediaStore.Video.Thumbnails._ID};        
				Cursor videoCursor = managedQuery(videoUri, projection, null, null, null);
	
				if(videoCursor != null && videoCursor.moveToNext()){
				        int videoIDCol = videoCursor.getColumnIndex(MediaStore.Video.Thumbnails._ID);
				        id = videoCursor.getLong(videoIDCol);
				}
				try{
					BitmapFactory.Options options=new BitmapFactory.Options();
					options.inSampleSize = 1;
					Bitmap curThumb = MediaStore.Video.Thumbnails.getThumbnail(getContentResolver(), id, MediaStore.Video.Thumbnails.MICRO_KIND, options);//비트맵에 이미지가 저장된다.
					iv.setImageBitmap(curThumb);
				}catch(Exception e){
				    e.printStackTrace();
				}
			}else{
				Toast.makeText(getApplicationContext(), "Video File 가져오기 실패",Toast.LENGTH_SHORT).show();
			}
			break;
		case requestCodeAudio:
			if (resultCode == RESULT_OK){
				Uri audioUri = data.getData();
//				File f = new File(audioUri.fromFile));
				String temp = audioUri.getPath();
//				temp = f.getName();
				Toast.makeText(getApplicationContext(), temp,Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(getApplicationContext(), "Audio File 가져오기 실패",Toast.LENGTH_SHORT).show();
			}
			break;
		}
		Cursor c = managedQuery(data.getData(), null,null,null,null);
	    c.moveToNext();
	    String path = c.getString(c.getColumnIndex(MediaStore.MediaColumns.DATA));
	    File f = new File(path);
	    if(f != null && f.canRead())
	    {
	    	try {
				DataInputStream dis = new DataInputStream(new FileInputStream(f));
				DataOutputStream dos = new DataOutputStream(mSock.socket.getOutputStream());
				PrintWriter out = new PrintWriter(mSock.networkWriter, true);
				if (requestCode == requestCodeImage){
					out.println("image");
				}else if(requestCode == requestCodeVideo){
					out.println("video");
				}else if(requestCode == requestCodeAudio){
					out.println("audio");
				}
				
				byte[] buf = new byte[1024];
				int len = 0;
				while((len = dis.read(buf)) != -1)
				{
					dos.write(buf, 0, len);
//					dos.flush();	//jinmantest			
				}
				dos.flush();	//jinmantest
//				dos.close();
				Toast.makeText(getApplicationContext(), "서버로 파일 전송",Toast.LENGTH_SHORT).show();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}    
    
	@Override
	protected void onPause() {
		super.onPause();
	}
	public void onReceived(String html)
	{
        ChatUserItem temp;
        
        temp = new ChatUserItem(html, 0);
        list.add(temp);
        adapter.notifyDataSetChanged();
	}
	public static ChatRoom getInstance()
	{
		return new ChatRoom();
	}
}
