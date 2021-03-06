package GuiElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.file.Path;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import fileSending.TestPane;

public class FileTransferPanelS extends JPanel{

	private static final long serialVersionUID = 1L;
	private static int totalNum;
	private int index;
	private TestPane progBar;
	private JPanel panel;
	private Path filepath;
	private JPanel panel_1;
	private JLabel lblFile;
	private JLabel lblStatus;
	//private TitledBorder t = new TitledBorder(new LineBorder(new Color(192, 192, 192)), null,TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(128, 128, 128));
	
	public FileTransferPanelS(Path filepath, String timeStamp){
		String filename =filepath.getFileName().toString();		
		this.filepath = filepath;
		totalNum++;
		index=totalNum;		
		setBackground(Color.WHITE);
		setMaximumSize(new Dimension(3000,1000));
		setPreferredSize(new Dimension(500,120));
		setBorder(new MatteBorder(0, 3, 0, 0, (Color) new Color(0, 204, 204)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{500};
		gridBagLayout.rowHeights = new int[]{30,25};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0};
		setLayout(gridBagLayout);
		createInsidePanel(filename, timeStamp);
		
	}

	
	private void createInsidePanel(String filename, String timeStamp){
	
		panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{200};
		gbl_panel.rowHeights = new int[]{20,10};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{1.0};
		panel.setLayout(gbl_panel);
		
		progBar = new TestPane();
		progBar.setVisible(false);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		
		lblFile = new JLabel(filename);
		lblFile.setBounds(30, 12, 288, 28);
		lblFile.setFont(new Font("Ubuntu", Font.PLAIN, 14));
		panel_1.add(lblFile);
		
		lblStatus = new JLabel("Awaiting Response");
		lblStatus.setBounds(30, 0, 170, 15);
		lblStatus.setForeground(new Color(128, 128, 128));
		panel_1.add(lblStatus);
		GridBagConstraints gbc_progBar = new GridBagConstraints();
		gbc_progBar.anchor= GridBagConstraints.SOUTHWEST;
		gbc_progBar.gridx = 0;
		gbc_progBar.gridy = 1;
		panel.add(progBar, gbc_progBar);
	
	
		JLabel lblTimestamp = new JLabel(timeStamp);
		lblTimestamp.setFont(new Font("Ubuntu Light", Font.PLAIN, 10));
		lblTimestamp.setBackground(Color.WHITE);
		
		GridBagConstraints gbc_lblTimestamp = new GridBagConstraints();
		gbc_lblTimestamp.gridx = 0;
		gbc_lblTimestamp.gridy = 1;
		gbc_lblTimestamp.anchor = GridBagConstraints.NORTHEAST;
		add(lblTimestamp, gbc_lblTimestamp);
	
	
	}
	public int getIndex()
	{
		return index;
	}
	
	public Path getFilePath()
	{
		return filepath;
	}
	public void showMsg(String msg){
	
		lblStatus.setText(msg);
		revalidate();
	}
	
	public void onAcceptance(){
		setBackground(new Color(180,250,180));
		panel_1.setBackground(new Color(180,250,180));
		panel.setBackground(new Color(180,250,180));
		progBar.setBackground(new Color(180,250,180));
		lblStatus.setText("Sending...");
		getParent().revalidate();
		getParent().repaint();
		
	}
	
	public void onReject(){
		setBackground(new Color(255,228,228));
		panel_1.setBackground(new Color(255,228,228));
		panel.setBackground(new Color(255,228,228));
		progBar.setBackground(new Color(255,228,228));
		lblStatus.setText("File Transfer Rejected");
		getParent().revalidate();
		getParent().repaint();
	}
	
	public TestPane getprogbar()
	{
		return progBar;
	}
	public void showDeliveryStatus(boolean s){ 
		
		JLabel lbl_cnfrmMessage;
		if(s)
		{
			lbl_cnfrmMessage=new JLabel("Message Delivered");
			lbl_cnfrmMessage.setForeground(new Color(50, 155, 50));
			lbl_cnfrmMessage.setFont(new Font("Ubuntu Light", Font.PLAIN, 10));
			
			GridBagConstraints gbc_lbl_cnfrmMessage = new GridBagConstraints();
			gbc_lbl_cnfrmMessage.gridx = 0;
			gbc_lbl_cnfrmMessage.gridy = 1;
			gbc_lbl_cnfrmMessage.anchor = GridBagConstraints.SOUTHEAST;
			add(lbl_cnfrmMessage, gbc_lbl_cnfrmMessage);
			revalidate();
		}
		else
		{
			lbl_cnfrmMessage=new JLabel("No Confirmation Received");
			lbl_cnfrmMessage.setForeground(new Color(205, 0, 50));
			lbl_cnfrmMessage.setFont(new Font("Ubuntu Light", Font.PLAIN, 10));
			
			GridBagConstraints gbc_lbl_cnfrmMessage = new GridBagConstraints();
			gbc_lbl_cnfrmMessage.gridx = 0;
			gbc_lbl_cnfrmMessage.gridy = 1;
			gbc_lbl_cnfrmMessage.anchor = GridBagConstraints.SOUTHEAST;
			add(lbl_cnfrmMessage, gbc_lbl_cnfrmMessage);
			revalidate();
		}
	}
}
