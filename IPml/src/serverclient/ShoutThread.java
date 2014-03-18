/*
 * This is the client file
 */
package serverclient;
import globalfunctions.IpAddress;

import java.io.*;
import java.net.*;

public class ShoutThread implements Runnable 
{
	protected DatagramSocket socket;
	protected String macadd;
	protected String ipadd1=null;
	protected String ipadd2=null;
	protected String user;
	
	public ShoutThread(String ipadd1, String ipadd2)
	{
		// get a datagram socket
		this.macadd=Mainstart.myid;
		this.ipadd1=ipadd1;
		this.ipadd2=ipadd2;
		this.user = Mainstart.myusername;
		this.socket= Mainstart.socket;
	}
	public ShoutThread()
	{
		// get a datagram socket
		this.socket = Mainstart.socket;
		this.macadd=Mainstart.myid;
		this.user = Mainstart.myusername;;
	}

	@Override
	public void run()
	{  
		Thread.currentThread().setName("ShoutThread");
		try
		{
			if(ipadd1!=null && ipadd2!=null)
			{
				for (long ip1=IpAddress.ipToLong(InetAddress.getByName(ipadd1));ip1<=IpAddress.ipToLong(InetAddress.getByName(ipadd2));ip1++)
				{// send request
			        byte[] buf = new byte[256];
			        buf= new String("D:C:"+macadd+":"+System.getProperty("os.name")+":"+InetAddress.getLocalHost().getHostName()+":"+user).getBytes();
			        InetAddress address = IpAddress.LongToip(ip1);
			        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 3333);
                	//synchronized(socket)
                	//{
                		socket.send(packet);
                	//}

				}
			}
			else
			{
				   byte[] buf = new byte[256];
				   buf= new String("D:C:"+macadd+":"+System.getProperty("os.name")+":"+InetAddress.getLocalHost().getHostName()+":"+user).getBytes();
			       InetAddress address = InetAddress.getByName("255.255.255.255");
			       DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 3333);
               	//synchronized(socket)
               	//{
               		socket.send(packet);
               	//}

			}
		} 
		catch (UnknownHostException e) 
		{
			System.err.print("Unable to find IP of current machine");
		}
		catch (IOException except)
        {
        	System.err.print("Network Problem : Unable to send packets!");
        	System.exit(0);
        }
     
    }
}