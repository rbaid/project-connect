/*
 * open license stuff.
 * This is the main file which will start the two threads for server and client.
 * Right now, it only starts the server thread
 * */

package serverclient;
import globalfunctions.Contact;
import globalfunctions.IpAddress;

import java.io.*;
import java.net.SocketException;
import java.util.HashMap;


public class Mainstart {
	
	public static void main(String[] args) throws IOException
    {
		String auth=IpAddress.IdentityMac();
		if (auth==null)
			throw new IOException("Network Problems detected!");
		
		System.out.println("My identity "+auth);
		HashMap <String,Contact> people = new HashMap <String,Contact> ();
		//Set <Contact> people = new HashSet <Contact>(); 
		try{
        new ListenThread(auth,"User", people).start();
		}catch(SocketException ex)
		{
			System.err.print("Unable to initiate connection: Port maybe in use already");
		}
        new ShoutThread(auth, "User").start();


    }
}
