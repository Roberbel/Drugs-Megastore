 package GUI;

import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame{
	
	private Container contenedor;
	private JPanel panelCentral;
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelEste; 
	private JPanel panelOeste;
	
		
	public static void main(String[] args) {
		MainWindow window = new MainWindow();
		window.setVisible(true);

	}
	
	MainWindow(){
		
		contenedor = getContentPane();
		contenedor.setLayout(new BorderLayout());
		panelNorte = new JPanel();
		panelSur = new JPanel();
		panelOeste = new JPanel();
		panelEste = new JPanel();
		panelCentral = new JPanel();
		
		panelNorte.add(new JButton ("North"));
		panelSur.add(new JButton ("South"));
		panelEste.add(new JButton ("East"));
		panelOeste.add(new JButton ("West"));
		panelCentral.add(new JButton("Centro"));
		
		contenedor.add(panelCentral, BorderLayout.CENTER);
		contenedor.add(panelNorte, BorderLayout.NORTH);
		contenedor.add(panelSur, BorderLayout.SOUTH);
		contenedor.add(panelEste, BorderLayout.EAST);
		contenedor.add(panelOeste, BorderLayout.WEST);
		
		
		setSize(1000, 900);
		
	}
	
}
