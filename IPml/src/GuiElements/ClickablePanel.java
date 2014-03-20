package GuiElements;

import globalfunctions.Contact;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ClickablePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel username = new JLabel();//this holds the username
	
	 public ClickablePanel(Contact sperson){
		
		//set all properties of the panel here
		setMaximumSize(new Dimension(30000,50));
		setPreferredSize(new Dimension(250,50));
		setBackground(Color.WHITE);
		setLayout(null);
		
		final Contact person=sperson;
		//panel properties end here
		
		//panel event handling here
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2)
				person.StartChat();
			}
		});

		/*components in the panel*/
		
			//username labels 
			this.username.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.username.setBounds(115, 12, 70, 26);
			this.username.setText(person.getHostname());
			this.add(username);
			
			//TODO user image labels
			
			//TODO options button
	}
}