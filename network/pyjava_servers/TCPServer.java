import java.io.*;
import java.net.*;

class TCPServer{
    public static void main(String[] argv)throws Exception{
	String clientSentence,capitalizedSentence;
	ServerSocket welcomeSocket=new ServerSocket(7777);
	System.out.println("TCP Server Listening on port 7777");
	while(true){
	    Socket connectionSocket=welcomeSocket.accept();
	    BufferedReader inFromClient=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	    DataOutputStream outToClient=new DataOutputStream(connectionSocket.getOutputStream());
	    clientSentence=inFromClient.readLine();
	    System.out.println("Received from client: "+ clientSentence );
	    capitalizedSentence=clientSentence.toUpperCase()+"\n";
	    outToClient.writeUTF(capitalizedSentence);
	    System.out.println("Sent back to client: "+ capitalizedSentence);
	}
    }
}
