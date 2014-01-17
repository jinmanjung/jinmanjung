import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer{
  private ServerSocket server;
  private BManager bMan=new BManager();  // 메시지 방송자
  public ChatServer(){}
  
  String msg = null;
  
  void startServer(){
    try{
      server = new ServerSocket(7878);
      System.out.println("서버소켓이 생성되었습니다.");
      while(true){
        Socket socket=server.accept();
 
        // 클라이언트와 통신하는 스레드를 생성하고 실행시킨다.
        new Chat_Thread(socket).start();
 
        // 방송자의 리스트에 socket을 추가한다.
        bMan.add(socket);
 
        // 방송자는 모든 클라이언트에게 현재 접속 인원의 수를 전송한다.
//        bMan.sendClientInfo();	//jinmantest
      }
    }catch(Exception e){
      System.out.println(e); 
    }
  }
  
  public static void main(String[] args){
	ChatServer server = new ChatServer();

	server.startServer();
  }
 
  // 클라이언트와 통신하는 스레드 클래스
  class Chat_Thread extends Thread{
    Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    
    Chat_Thread(Socket socket){
      this.socket=socket;
    }
    public void run(){
      try{
        reader=new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
        writer=new PrintWriter(socket.getOutputStream(), true);
//        String msg;

          // 입력 스트림으로부터 메시지를 얻는다.
          while((msg=reader.readLine()) !=null){ 
        	  System.out.println(msg);
        	  if(msg.equals("image"))
        	  {
        		  System.out.println("msg.equals(image)");
        		  bMan.sendToAllExceptMe(msg, socket);	    // 모든 클라이언트에게 이미지 파일를 전송한다.
        		  bMan.saveFile(socket);
        	  }
        	  else if(msg.equals("video"))
        	  {
        		  System.out.println("msg.equals(video)");
        		  bMan.sendToAllExceptMe(msg, socket);	    // 모든 클라이언트에게 이미지 파일를 전송한다.
        		  bMan.saveFile(socket);
        	  }
        	  else if(msg.equals("audio"))
        	  {
        		  System.out.println("msg.equals(audio)");
        		  bMan.sendToAllExceptMe(msg, socket);	    // 모든 클라이언트에게 오디오 파일를 전송한다.
        		  bMan.saveFile(socket);
        	  }
        	  else
        	  {	  
        		  System.out.println("msg.equals(else)");
        		  bMan.sendToAllExceptMe(msg, socket);	    // 모든 클라이언트에게 메시지를 전송한다.
        	  }
        }
      }catch(Exception e){
    	  System.out.println(e);
      }finally{
    	  
        try{
          // 방송자의 리스트에서 socket을 제거한다.
       								//jinmantest
          bMan.remove(socket);
          if(reader!=null) reader.close();
          if(writer!=null) writer.close();
          if(socket!=null) socket.close();
          reader=null; writer=null; socket=null;
 
          System.out.println("클라이언트가 나갔습니다.");
        
          // 모든 클라이언트에게 현재 접속 인원의 수를 전송한다.
//          bMan.sendClientInfo();	//jinmantest
        }catch(Exception e){}
      }
    }
  }
 
  // 메시지 방송자 클래스, Vector를 상속한다.
  class BManager extends Vector{
    BManager(){}
    void add(Socket sock){  // 소켓을 추가한다.
      super.add(sock);
    }
    void remove(Socket sock){  // 소켓을 제거한다.
      super.remove(sock);
    }
    
    // 모든 클라이언트에게 msg를 전송한다. 동기화 메소드
    synchronized void sendToAllExceptMe(String msg, Socket s){
      PrintWriter writer=null;  // 출력 스트림
      Socket sock;   // 소켓
 
      for(int i=0; i<size(); i++){  // 소켓의 개수만큼 반복 실행
        sock=(Socket)elementAt(i);  // i번째 소켓을 얻는다.
        if(s == sock) continue; // 임시적
        try{
 
          // i번째 소켓의 출력 스트림을 얻는다.
          writer=new PrintWriter(sock.getOutputStream(), true);
 
        }catch(IOException ie){}
 
        // i번째 소켓의 출력 스트림으로 msg를 출력한다.
        if(writer!=null)writer.println(msg);
      }
    }
    // 모든 클라이언트에게 msg를 전송한다. 동기화 메소드
    synchronized void sendToAll(String msg){
      PrintWriter writer=null;  // 출력 스트림
      Socket sock;   // 소켓
 
      for(int i=0; i<size(); i++){  // 소켓의 개수만큼 반복 실행
        sock=(Socket)elementAt(i);  // i번째 소켓을 얻는다.
        try{
 
          // i번째 소켓의 출력 스트림을 얻는다.
          writer=new PrintWriter(sock.getOutputStream(), true);
 
        }catch(IOException ie){}
 
        // i번째 소켓의 출력 스트림으로 msg를 출력한다.
        if(writer!=null)writer.println(msg);
      }
    }
    
    synchronized void saveFile(Socket sendSock){
    	Socket recvSock = null;
    	
    	for(int i=0; i<size(); i++){  // 소켓의 개수만큼 반복 실행
    		recvSock=(Socket)elementAt(i);  // i번째 소켓을 얻는다.
    		if(sendSock != recvSock) break;	// 임시적... 채팅인원이 2명일때로 가정한다..
          }

    	try {
    		File f = new File("C:\\", "tmp.jpg");
    		if(msg.equals("image")){
    			System.out.println("파일속성:image");
    			f = new File("C:\\", "test.jpg");
    		}
    		else if(msg.equals("video")){
    			System.out.println("파일속성:video");
    			f = new File("C:\\", "test.3gp");
    		}
    		else if(msg.equals("audio")){
    			System.out.println("파일속성:audio");
    			f = new File("C:\\", "test.amr");
    		}
    		System.out.println("파일명  "+f.getName());
//        	File f = new File("C:\\", "tmp.jpg");
        	System.out.println(f.getTotalSpace());
    		DataInputStream dis = new DataInputStream(sendSock.getInputStream());
    		DataOutputStream dos = new DataOutputStream(recvSock.getOutputStream());
    		FileOutputStream fos = new FileOutputStream(f);

    		int len = 0;
    		byte[] buf = new byte[1024];
    		while((len = dis.read(buf)) != -1)
    		{
    			fos.write(buf, 0, len);
    			fos.flush();
    			if(len < 1024) break;	// 꼼수다
    			System.out.println("파일받는중  "+len);
    			
    			try {
					Thread.sleep(len);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    		}
//    		fos.flush();
    		fos.close();
    		FileInputStream fis = new FileInputStream(f);
    		
    		while((len = fis.read(buf)) != -1)
    		{
    			dos.write(buf, 0, len);
    			dos.flush();
    			if(len < 1024) break;	// 꼼수다
    			System.out.println("파일보내는중   "+len);
    			
    			try {
					Thread.sleep(len);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    		}
    		System.out.println("파일전송완료");
    		dos.flush();
    		fis.close();
    		System.out.println(f.getName());
    		System.out.println(f.getTotalSpace());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }

	// 모든 클라이언트에게 현재 채팅 인원의 수를 전송한다.
    
    synchronized void sendClientInfo(){
      String info="현재 채팅 인원: "+size();
      System.out.println(info);
      sendToAll(info);
    }
  }
}