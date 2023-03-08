import java.io.*;
import java.net.*;

class TCPClient{
    public static void main(String[] argv)throws Exception{
	String sentence;
	String modifiedSentence;
	BufferedReader inFromUser=new BufferedReader(new InputStreamReader(System.in));
	Socket clientSocket=new Socket("localhost",7777);
	DataOutputStream outToServer=new DataOutputStream(clientSocket.getOutputStream());
	BufferedReader inFromServer=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	sentence = inFromUser.readLine();
	outToServer.writeUTF(sentence+"\n");
	modifiedSentence=inFromServer.readLine();
	System.out.println("From Server----->"+modifiedSentence);
	clientSocket.close();
    }
}
