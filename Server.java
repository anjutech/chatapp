

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Server {

	ServerSocket server;
	Socket socket;

	BufferedReader br;
	PrintWriter out;

//	constructor
	public Server() {
		try {
			server = new ServerSocket(8080);
			System.out.println("Server is ready to accept connection");
			System.out.println("waiting------");
			socket = server.accept();

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
					System.out.println("Client terminated the Chat!");
					break;
				}
	    		   System.out.println("Client : " + msg);
	    	   
	    	   
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

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("This is a Server Babey ------ that is Going to start!");
// Constructor ko ase call krte hai-----server.server() ase method ko call krte h;
		new Server();

	}

}
