package GUI;

import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame{
	
	private Container container;
	private JPanel centralPanel;
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel eastPanel; 
	private JPanel westPanel;
	
		
	public static void main(String[] args) {
		MainWindow window = new MainWindow();
		window.setVisible(true);

	}
	
	MainWindow(){
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Drug Megastore - database managment");
		
		container = getContentPane();
		container.setLayout(new BorderLayout());
		northPanel = new JPanel(new FlowLayout());
		southPanel = new JPanel(new FlowLayout());
		westPanel = new JPanel(new FlowLayout());
		eastPanel = new JPanel(new FlowLayout());
		centralPanel = new JPanel(new FlowLayout());
		
		northPanel.add(new JButton ("North"));
		southPanel.add(new JButton ("South"));
		eastPanel.add(new JButton ("East"));
		westPanel.add(new JButton ("West"));
		centralPanel.add(new JButton("Centro"));
		
		
		container.add(centralPanel, BorderLayout.CENTER);
		container.add(northPanel, BorderLayout.NORTH);
		container.add(southPanel, BorderLayout.SOUTH);
		container.add(eastPanel, BorderLayout.EAST);
		container.add(westPanel, BorderLayout.WEST);
		
		setSize(1000, 900);
		
	}
	
}
