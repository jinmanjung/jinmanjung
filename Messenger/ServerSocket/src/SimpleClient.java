///////////////////////////////SimpleClient.java
/*

1. TCP/IP ���������� ���� ���α׷� ����
2. ����  - Ŭ���̾�Ʈ ����
 - ������ �����ϸ� ������ ������� ������ �پƼ� client â�� ���
 3. �ʿ� API
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

   System.out.println( "������ ���� : " + value );

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