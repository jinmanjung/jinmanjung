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
  private BManager bMan=new BManager();  // �޽��� �����
  public ChatServer(){}
  
  String msg = null;
  
  void startServer(){
    try{
      server = new ServerSocket(7878);
      System.out.println("���������� �����Ǿ����ϴ�.");
      while(true){
        Socket socket=server.accept();
 
        // Ŭ���̾�Ʈ�� ����ϴ� �����带 �����ϰ� �����Ų��.
        new Chat_Thread(socket).start();
 
        // ������� ����Ʈ�� socket�� �߰��Ѵ�.
        bMan.add(socket);
 
        // ����ڴ� ��� Ŭ���̾�Ʈ���� ���� ���� �ο��� ���� �����Ѵ�.
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
 
  // Ŭ���̾�Ʈ�� ����ϴ� ������ Ŭ����
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

          // �Է� ��Ʈ�����κ��� �޽����� ��´�.
          while((msg=reader.readLine()) !=null){ 
        	  System.out.println(msg);
        	  if(msg.equals("image"))
        	  {
        		  System.out.println("msg.equals(image)");
        		  bMan.sendToAllExceptMe(msg, socket);	    // ��� Ŭ���̾�Ʈ���� �̹��� ���ϸ� �����Ѵ�.
        		  bMan.saveFile(socket);
        	  }
        	  else if(msg.equals("video"))
        	  {
        		  System.out.println("msg.equals(video)");
        		  bMan.sendToAllExceptMe(msg, socket);	    // ��� Ŭ���̾�Ʈ���� �̹��� ���ϸ� �����Ѵ�.
        		  bMan.saveFile(socket);
        	  }
        	  else if(msg.equals("audio"))
        	  {
        		  System.out.println("msg.equals(audio)");
        		  bMan.sendToAllExceptMe(msg, socket);	    // ��� Ŭ���̾�Ʈ���� ����� ���ϸ� �����Ѵ�.
        		  bMan.saveFile(socket);
        	  }
        	  else
        	  {	  
        		  System.out.println("msg.equals(else)");
        		  bMan.sendToAllExceptMe(msg, socket);	    // ��� Ŭ���̾�Ʈ���� �޽����� �����Ѵ�.
        	  }
        }
      }catch(Exception e){
    	  System.out.println(e);
      }finally{
    	  
        try{
          // ������� ����Ʈ���� socket�� �����Ѵ�.
       								//jinmantest
          bMan.remove(socket);
          if(reader!=null) reader.close();
          if(writer!=null) writer.close();
          if(socket!=null) socket.close();
          reader=null; writer=null; socket=null;
 
          System.out.println("Ŭ���̾�Ʈ�� �������ϴ�.");
        
          // ��� Ŭ���̾�Ʈ���� ���� ���� �ο��� ���� �����Ѵ�.
//          bMan.sendClientInfo();	//jinmantest
        }catch(Exception e){}
      }
    }
  }
 
  // �޽��� ����� Ŭ����, Vector�� ����Ѵ�.
  class BManager extends Vector{
    BManager(){}
    void add(Socket sock){  // ������ �߰��Ѵ�.
      super.add(sock);
    }
    void remove(Socket sock){  // ������ �����Ѵ�.
      super.remove(sock);
    }
    
    // ��� Ŭ���̾�Ʈ���� msg�� �����Ѵ�. ����ȭ �޼ҵ�
    synchronized void sendToAllExceptMe(String msg, Socket s){
      PrintWriter writer=null;  // ��� ��Ʈ��
      Socket sock;   // ����
 
      for(int i=0; i<size(); i++){  // ������ ������ŭ �ݺ� ����
        sock=(Socket)elementAt(i);  // i��° ������ ��´�.
        if(s == sock) continue; // �ӽ���
        try{
 
          // i��° ������ ��� ��Ʈ���� ��´�.
          writer=new PrintWriter(sock.getOutputStream(), true);
 
        }catch(IOException ie){}
 
        // i��° ������ ��� ��Ʈ������ msg�� ����Ѵ�.
        if(writer!=null)writer.println(msg);
      }
    }
    // ��� Ŭ���̾�Ʈ���� msg�� �����Ѵ�. ����ȭ �޼ҵ�
    synchronized void sendToAll(String msg){
      PrintWriter writer=null;  // ��� ��Ʈ��
      Socket sock;   // ����
 
      for(int i=0; i<size(); i++){  // ������ ������ŭ �ݺ� ����
        sock=(Socket)elementAt(i);  // i��° ������ ��´�.
        try{
 
          // i��° ������ ��� ��Ʈ���� ��´�.
          writer=new PrintWriter(sock.getOutputStream(), true);
 
        }catch(IOException ie){}
 
        // i��° ������ ��� ��Ʈ������ msg�� ����Ѵ�.
        if(writer!=null)writer.println(msg);
      }
    }
    
    synchronized void saveFile(Socket sendSock){
    	Socket recvSock = null;
    	
    	for(int i=0; i<size(); i++){  // ������ ������ŭ �ݺ� ����
    		recvSock=(Socket)elementAt(i);  // i��° ������ ��´�.
    		if(sendSock != recvSock) break;	// �ӽ���... ä���ο��� 2���϶��� �����Ѵ�..
          }

    	try {
    		File f = new File("C:\\", "tmp.jpg");
    		if(msg.equals("image")){
    			System.out.println("���ϼӼ�:image");
    			f = new File("C:\\", "test.jpg");
    		}
    		else if(msg.equals("video")){
    			System.out.println("���ϼӼ�:video");
    			f = new File("C:\\", "test.3gp");
    		}
    		else if(msg.equals("audio")){
    			System.out.println("���ϼӼ�:audio");
    			f = new File("C:\\", "test.amr");
    		}
    		System.out.println("���ϸ�  "+f.getName());
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
    			if(len < 1024) break;	// �ļ���
    			System.out.println("���Ϲ޴���  "+len);
    			
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
    			if(len < 1024) break;	// �ļ���
    			System.out.println("���Ϻ�������   "+len);
    			
    			try {
					Thread.sleep(len);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    		}
    		System.out.println("�������ۿϷ�");
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

	// ��� Ŭ���̾�Ʈ���� ���� ä�� �ο��� ���� �����Ѵ�.
    
    synchronized void sendClientInfo(){
      String info="���� ä�� �ο�: "+size();
      System.out.println(info);
      sendToAll(info);
    }
  }
}