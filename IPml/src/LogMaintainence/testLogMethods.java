package LogMaintainence;

import java.util.Date;

public class testLogMethods {

	public static void main(String args[])
	{
		Date date= new Date();                               
		String time = ""+date.getTime();
		int i=0;
		
		String userId = "user1", userMessage , userName;
		//int i=0;
		
		ChatLogging newLog = new ChatLogging("user1", "rajat");
		new Thread(newLog).start();
		
		
		
		
		//GettingChatLogs oldChat = new GettingChatLogs();
		
		/*while(i<2)
		{
			if(i%2==0)
			{
				userId = "user1"; userName = "user"; userMessage = "hi bro";
			}
			else
			{
				userId = "user1"; userName = "me"; userMessage = "yeah bro";
			}
				  
			newLog.logCreate(userId, userName, userMessage, time); 
			i++;
		}*/
		//GettingChatLogs.readLog("78E400ACD134"); 
		
		GettingChatLogs.readLog("user1");
	
	}
}
