package GUI;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

import model.LogInManager;
import pojos.User;
import pojos.User.UserClass;

public class MainWindow extends JFrame {

	private Container contenedor;
	private JPanel panelCentral;
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelEste;
	private JPanel panelOeste;

	public static void main(String[] args) {
		User testUser = new User("Thevini98", "Testeo");
		LogInManager logInManager = new LogInManager(testUser);

		try {
			if (logInManager.checkExistance()) {
				if (logInManager.checkPassword()) {
					System.out.println("Log in correcto");
					System.out.println(logInManager.getCompleteUser().toString());
				} else {
					System.out.println("Introduzca la Contraseña correcta");
				}
			} else {
				System.out.println("El usuario no existe");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	MainWindow() {

		contenedor = getContentPane();
		contenedor.setLayout(new BorderLayout());
		panelNorte = new JPanel();
		panelSur = new JPanel();
		panelOeste = new JPanel();
		panelEste = new JPanel();
		panelCentral = new JPanel();

		panelNorte.add(new JButton("North"));
		panelSur.add(new JButton("South"));
		panelEste.add(new JButton("East"));
		panelOeste.add(new JButton("West"));
		panelCentral.add(new JButton("Centro"));

		contenedor.add(panelCentral, BorderLayout.CENTER);
		contenedor.add(panelNorte, BorderLayout.NORTH);
		contenedor.add(panelSur, BorderLayout.SOUTH);
		contenedor.add(panelEste, BorderLayout.EAST);
		contenedor.add(panelOeste, BorderLayout.WEST);

		setSize(1000, 900);

	}

}
