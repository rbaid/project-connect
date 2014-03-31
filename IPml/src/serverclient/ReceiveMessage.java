package serverclient;

import globalfunctions.Contact;
import globalfunctions.FileTransfer;

import java.sql.Timestamp;
import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import GuiElements.ChatWindowPanelReceiver;
import GuiElements.FileTransferPanel;

public class ReceiveMessage implements Runnable 
{
	String packdetails[] = null;
	//InetAddress ip = null;
	HashMap <String, Contact> people = null;
	Timestamp t = null;
	
	public ReceiveMessage(String [] packdetails, InetAddress ip, Timestamp t)
	{
		this.packdetails= packdetails;
		//this.ip = ip;
		this.t=t;
	}
	@Override
	public void run()
	{/*packdetails[1]=mac
        packdetails[2]=threadnumber of sending thread
         */
		Thread.currentThread().setName("ReceiveMessageThread");
		Contact person = (Contact) MainStart.people.get(packdetails[1]);
		if(packdetails[0].equals("M")) {
			//packdetails[3]=message
			ChatWindowPanelReceiver MessagePane = new ChatWindowPanelReceiver(new String(person.getUserName()+":"+packdetails[3]), "tsdfhjskdf");
			person.getWindow().chatconsole(MessagePane);
		}
		else if(packdetails[0].equals("S")) {
			//packdetails[3]=sendingPanelId
			//packdetails[4]=fileheader
			String fileP = FileTransfer.getFilePath(packdetails[4]);
			Path filepath = Paths.get(fileP.trim());
			int sendPanelId = Integer.parseInt(packdetails[3]);
			FileTransferPanel ftPane = new FileTransferPanel(person,filepath,sendPanelId);
			person.getWindow().chatconsole(ftPane);
		}
		
	}
	/*public static void requestFile(char fileType,String filePath, String ipAddress) {
		
		 
   	 byte[] buf = new byte[256];
   	 System.out.println("file path: " + filePath);
   	 
		 buf= new String(fileType+":R-"+filePath).getBytes();
	     InetAddress address = null;
	     
		try {
			address = InetAddress.getByName(ipAddress);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     for(int i=0;i<buf.length;i++) {
	    	 System.out.print((char)buf[i]);
	     }
	     DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 3333);
	     try {
	    	 DatagramSocket multicastSocket = new DatagramSocket();
			 multicastSocket.send(packet);
			 System.out.println("Packet sent ");
			 multicastSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}