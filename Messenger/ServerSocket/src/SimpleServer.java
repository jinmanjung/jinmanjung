///////////////////////////////SimpleServer.java

/*
1. TCP/IP 연결지향형 소켓 프로그램 개발
2. 로직 - 서버 개발
 - client가 접소갛면 "넌 접속" 메시지를 client에 단순 출력
 - 동시 접속자 미 고려
 3. 필요 API
*/

import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;

public class SimpleServer
{
 public static void main( String[] args ){

  ServerSocket server = null;
  Socket clientServer = null;
  OutputStream os = null;
  PrintWriter out = null;

  try{

   server = new ServerSocket( 1209 );
   System.out.println("접속 승인 준비중...");
   clientServer = server.accept();
   os = clientServer.getOutputStream();
   out = new PrintWriter( new BufferedOutputStream( os ) );

   System.out.println("넌 접속");
   out.println("넌 접속");
   

  }catch( IOException ioe ){
   ioe.printStackTrace();
  }finally{

   try{

    out.close();
    clientServer.close();

   }catch( IOException ioe ){}
  }


 }
}
