
import java.net.*;
import java.io.*;

public class Client {

	Socket socket;
	BufferedReader br;
	PrintWriter out;
	
	public  Client() {
	try {
		
		System.out.println("sending request to server!");
		socket = new Socket("127.0.0.1",8080);
		System.out.println("Connection Done!");
		
		
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
		
		startReading();
		startWriting();
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	}

	private void startReading() {
		// thread- data user se lega and send karega client tak
		   Runnable r1=()->{
	    	   System.out.println("Reader Started");
	    	   
	    	   while(true) {
	    		   try {
	    		   String msg = br.readLine();
	    		   if (msg.equals("bye")) {
					System.out.println("Server terminated the Chat!");
					break;
				}
	    		   System.out.println("Server : " + msg);
	    	   
	    	   
	    	   }catch (Exception e) {
				// TODO: handle exception
	    		   e.printStackTrace();
			}
	    	   }
	       };
	       
	       new Thread(r1).start();
	}

	private void startWriting() {
		// thread-read karke deta rhega
       Runnable r2=()->{
    	   System.out.println("Writer Started");
    	   
    	   while(true) {
    		   try {
    		   
    			   BufferedReader br1= new BufferedReader(new InputStreamReader(System.in));
    			   String conten = br1.readLine();
    			   out.println(conten);
    			   out.flush();
    	   
    	   }catch (Exception e) {
			// TODO: handle exception
    		   e.printStackTrace();
		}
    	   }
       };
       new Thread(r2).start();

	}
	
	public static void   main(String[] args) {
		// TODO Auto-generated method stub
System.out.println("This is Client  Babey!");
new Client();
	}

}
