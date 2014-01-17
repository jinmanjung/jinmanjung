///////////////////////////////SimpleClient.java
/*

1. TCP/IP 연결지향형 소켓 프로그램 개발
2. 로직  - 클라이언트 개발
 - 서버에 접속하면 서버가 출려갛ㄴ 데이터 바아서 client 창에 출력
 3. 필요 API
 java.net.Socket
 java.io.PrintWriter

*/
import java.net.Socket;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SimpleClient
{
 public static void main( String[] args ){

  Socket clientSocket = null;
  BufferedReader br = null;
  String value = null;

  try{

   clientSocket = new Socket( "192.168.0.3", 1209 );
   br = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );
   value = br.readLine();

   System.out.println( "서버가 전송 : " + value );

  }catch( IOException ioe ){

   ioe.printStackTrace();

  }finally{

   try{

    br.close();
    clientSocket.close();

   }catch( IOException ioe ){}

  }

 }
}