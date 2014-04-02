package LogMaintainence;
import globalfunctions.Contact;

import java.io.*;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import GuiElements.ChatWindowPanelReceiver;
import GuiElements.ChatWindowPanelSender;
import serverclient.MainStart;

public class GettingChatLogs extends Object{
	
	@SuppressWarnings("unchecked")
	public static void readLog(String userId)
	{
		String chatFileName = userId+".json";             // file name based on userId
		
		String oldPathString = System.getProperty("user.dir");
		String newPathString = oldPathString+"/chatlogs";
		
		File newPath = new File(newPathString);
		File chatFilePath = new File(newPath,chatFileName);

		String myId = MainStart.myID;		
		Contact person = MainStart.people.get(userId);         //person needed to get the correct chat window
		
		long sessionTraversalCount = 0;
		JSONParser parser = new JSONParser();
		 
		if(chatFilePath.exists())
		{
			try {
				Object obj = parser.parse(new FileReader(chatFilePath));
				JSONObject logInfo = (JSONObject)obj;											 
				               
				long sessionValue = (long)logInfo.get("lastUpdatedSession");
				
				sessionTraversalCount = sessionValue;
				
				JSONObject oldSessionObject = (JSONObject)logInfo.get("session");
				JSONArray oldMessageArray;
				int i=1;
				while(i<=sessionTraversalCount)
				{
					//System.out.println("session : "+i);
					
					oldMessageArray = (JSONArray)oldSessionObject.get(""+i);
					
					Iterator<JSONObject> oldMessageIterator = oldMessageArray.iterator();
					while (oldMessageIterator.hasNext()) 
					{        
						JSONObject messageObject = (JSONObject)oldMessageIterator.next();
						if(messageObject.get("userId").equals(myId))
						{
							String s1 = new String(messageObject.get("userName")+": "+messageObject.get("messageText"));
							ChatWindowPanelSender cwsp = new ChatWindowPanelSender(s1, messageObject.get("timeStamp").toString());
							person.getWindow().chatconsole(cwsp);
							
						}
						else
						{
							String s1 = new String(messageObject.get("userName")+": "+messageObject.get("messageText"));
							ChatWindowPanelReceiver cwrp = new ChatWindowPanelReceiver(s1, messageObject.get("timeStamp").toString());
							person.getWindow().chatconsole(cwrp);

						}
					 
					}
					System.out.println();
					i++;
				}
			
				}
		
				catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				} 
				catch (ParseException e) 
				{
					e.printStackTrace();
				}	
			}
		else
		{
			System.out.println("No chats exist\n");
		}
	}

}
