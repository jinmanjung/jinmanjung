///////////////////////////////SimpleServer.java

/*
1. TCP/IP ���������� ���� ���α׷� ����
2. ���� - ���� ����
 - client�� ���Ұ��� "�� ����" �޽����� client�� �ܼ� ���
 - ���� ������ �� ���
 3. �ʿ� API
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
   System.out.println("���� ���� �غ���...");
   clientServer = server.accept();
   os = clientServer.getOutputStream();
   out = new PrintWriter( new BufferedOutputStream( os ) );

   System.out.println("�� ����");
   out.println("�� ����");
   

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
