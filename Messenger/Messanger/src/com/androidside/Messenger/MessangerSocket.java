package com.androidside.Messenger;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
 
public class MessangerSocket { 

	/* -- [temporary add] socket client -- */
	private String html = ""; 
    private Handler mHandler; 
    public Socket socket; 
//  private String name; 
    public BufferedReader networkReader; 
    public BufferedWriter networkWriter; 
	private String ip = "192.168.0.2"; // 동철 사설 IP

	private int port = 7878; // PORT번호 
    private ChatRoom cr;
    private boolean bThread = true;
	/* -- [temporary add] socket client -- */
    
    public MessangerSocket(ChatRoom cr) {
		this.cr = cr;
	}

	public void socketStop() {
        try { 
            socket.close();
            bThread = false;
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    public boolean socketStart() { 
        mHandler = new Handler(); 
        
        try { 
            setSocket(ip, port); 
        } catch (IOException e1) { 
            e1.printStackTrace(); 
            return false;
        } 
 
        checkUpdate.start();
        return true;
    } 
 
    public void sendMsg(String msg) {
        PrintWriter out = new PrintWriter(networkWriter, true); 
        out.println(msg);           	
    }

    private Thread checkUpdate = new Thread() {    
        public void run() { 
            try { 
                String line; 
                Log.w("ChattingStart", "Start Thread"); 
                while (true) { 
                    Log.w("Chatting is running", "chatting is running"); 
                    line = networkReader.readLine();
					String ess = Environment.getExternalStorageState();   
					String sdCardPath = null;   
					if(ess.equals(Environment.MEDIA_MOUNTED)) {   
					    sdCardPath = Environment.getExternalStorageDirectory().getAbsolutePath();   
					    Log.w("checkUpdate", "SD Card stored in " + sdCardPath);
					    
					    File dir = new File(sdCardPath + "/download");
					    if(dir.isFile()) {
					    	Log.w("checkUpdate", "Same name file stored in " + dir.getAbsolutePath());	// 나중에 처리
					    }
					    else if(dir.isDirectory()) {
					    	Log.w("checkUpdate", "Already folder stored in " + dir.getAbsolutePath());
					    }
					    else
					    {
					    	Log.w("checkUpdate", "else");
					    	dir.mkdirs();
					    }
					    
					} else {   
						Log.w("checkUpdate", "SD Card not ready!");   // 나중에 처리
					}
					DataInputStream dis;
                    if(line == null)
                    {
                    	break;
                    }
                    else if(line.equals("image"))
                	{
//                		DataInputStream dis;
        				try {

                        	html = "Start image download"; 
                        	mHandler.post(showUpdate);
/*        					
        					String ess = Environment.getExternalStorageState();   
        					String sdCardPath = null;   
        					if(ess.equals(Environment.MEDIA_MOUNTED)) {   
        					    sdCardPath = Environment.getExternalStorageDirectory().getAbsolutePath();   
        					    Log.w("checkUpdate", "SD Card stored in " + sdCardPath);
        					    
        					    File dir = new File(sdCardPath + "/download");
        					    if(dir.isFile()) {
        					    	Log.w("checkUpdate", "Same name file stored in " + dir.getAbsolutePath());	// 나중에 처리
        					    }
        					    else if(dir.isDirectory()) {
        					    	Log.w("checkUpdate", "Already folder stored in " + dir.getAbsolutePath());
        					    }
        					    else
        					    {
        					    	Log.w("checkUpdate", "else");
        					    	dir.mkdirs();
        					    }
        					    
        					} else {   
        						Log.w("checkUpdate", "SD Card not ready!");   // 나중에 처리
        					}
*/        					
        					File f = new File(sdCardPath + "/download", "test.jpg");
        	        		FileOutputStream fos = new FileOutputStream(f);

        	            	dis = new DataInputStream(socket.getInputStream());
        	            	int len;
        	        		byte[] buf = new byte[1024];
        	        		while((len = dis.read(buf)) != -1)
        	        		{
        	        			fos.write(buf, 0, len);
        	        			if(len < 1024){
        	        				Log.d("checkUpdate", "len < 1024");
        	        				break; // 나는 꼼수다
        	        			}
        	        			Log.d("checkUpdate", "loading");	
        	        		}
    	        			fos.flush();
        	        		fos.close();
        	        		f = null;
        	        		Log.d("checkUpdate", "image loading");
                        	html = "End image download"; 
                        	mHandler.post(showUpdate);

//                        	html = "image"; 	//다운받은 그림 보여주기
//                        	mHandler.post(showUpdate);
            				//이미지 썸네일 생성      
        				} catch (IOException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
              	}
                    else if(line.equals("video"))
                    {
        				try {
                        	html = "Start video download"; 
                        	mHandler.post(showUpdate);
    					File f = new File(sdCardPath + "/download", "test.3gp");
    	        		FileOutputStream fos = new FileOutputStream(f);

    	            	dis = new DataInputStream(socket.getInputStream());
    	            	int len;
    	        		byte[] buf = new byte[1024];
    	        		while((len = dis.read(buf)) != -1)
    	        		{
    	        			fos.write(buf, 0, len);
    	        			if(len < 1024){
    	        				Log.d("checkUpdate", "len < 1024");
    	        				break; // 나는 꼼수다
    	        			}
    	        			Log.d("checkUpdate", "loading");	
    	        		}
	        			fos.flush();
    	        		fos.close();
    	        		f = null;
    	        		Log.d("checkUpdate", "video loading");
                    	html = "End video download"; 
                    	mHandler.post(showUpdate);
                    	
//                    	html = "video"; 	
//                    	mHandler.post(showUpdate);
        				} catch (IOException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}                    	
                    }
                    else if(line.equals("audio"))
                    {
        				try {
                        	html = "Start audio download"; 
                        	mHandler.post(showUpdate);
    					File f = new File(sdCardPath + "/download", "test.amr");
    	        		FileOutputStream fos = new FileOutputStream(f);

    	            	dis = new DataInputStream(socket.getInputStream());
    	            	int len;
    	        		byte[] buf = new byte[1024];
    	        		while((len = dis.read(buf)) != -1)
    	        		{
    	        			fos.write(buf, 0, len);
    	        			if(len < 1024){
    	        				Log.d("checkUpdate", "len < 1024");
    	        				break; // 나는 꼼수다
    	        			}
    	        			Log.d("checkUpdate", "audio loading");	
    	        		}
	        			fos.flush();
    	        		fos.close();
    	        		f = null;
    	        		Log.d("checkUpdate", "loading");
                    	html = "End audio download"; 
                    	mHandler.post(showUpdate);                    	
//                    	html = "audio"; 	
//                    	mHandler.post(showUpdate);
        				} catch (IOException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				} 
                    }
                    else
                    {
                    	html = line; 
                    	mHandler.post(showUpdate);
                    }
                } 
            } catch (Exception e) { 
            } 
        } 
        	
    }; 
 
    private Runnable showUpdate = new Runnable() { 
        public void run() {

        	if(html.equals("image"))
        	{
//				Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
//				intent.setType("image/*");
//				startActivity(intent);
        		cr.onReceived(html);
        	}
        	else if(html.equals("video"))
        	{
        		cr.onReceived(html);
        	}
        	else if(html.equals("audio"))
        	{
        		cr.onReceived(html);
        	}
        	else
        	{
            	cr.onReceived(html);	//before src
        	}
                	
        } 
    }; 
 
    public void setSocket(String ip, int port) throws IOException {
/*	before src    	
        socket = new Socket();
        socket.connect(new InetSocketAddress(ip, port), 3000);
        networkWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 
        networkReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
*/
        try { 
            socket = new Socket(ip, port); 
            networkWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"euc-kr")); 
            networkReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"euc-kr")); 
        } catch (IOException e) { 
            System.out.println(e); 
            e.printStackTrace(); 
        }     	
    } 
}