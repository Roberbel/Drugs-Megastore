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
	
	//employee creation elements
	private JButton employeeAddingButton;
	private JTextField employeeAddingNameField;
	private JTextField employeeAddingSalaryField;
	private JTextField employeeAddingTelephoneField;
	private JTextField employeeAddingPositionField;
	private JTextField employeeAddingWarehouseIDField;
	private JButton employeeAddingPictureButton;
	private JLabel employeeAddingPictureBesideLabel;
		
	
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
		centralPanel.add(this.createEmployeeCreationPanel());
		
		
		container.add(centralPanel, BorderLayout.CENTER);
		container.add(northPanel, BorderLayout.NORTH);
		container.add(southPanel, BorderLayout.SOUTH);
		container.add(eastPanel, BorderLayout.EAST);
		container.add(westPanel, BorderLayout.WEST);
		
		setSize(1000, 900);
		
	}
	
	private JPanel createEmployeeCreationPanel() {
		
		JPanel employeeCreationPanel;
		BorderLayout layout;
		
		layout = new BorderLayout();
		employeeCreationPanel = new JPanel(layout);
		
		JPanel southPanel;
		JPanel centerPanel;
		
		centerPanel = new JPanel(new GridLayout(6, 2, 0, 25));
		southPanel = new JPanel(new FlowLayout());
		
		employeeAddingButton = new JButton("Add Employee");
		//employeeAddingButton.addActionListener(this);
		 
		southPanel.add(employeeAddingButton);
		
		employeeCreationPanel.add( southPanel, BorderLayout.SOUTH);
		Dimension d = new Dimension(300, 30);
		
		employeeAddingNameField = new JTextField();
		JLabel employeeAddingNameLabel = new JLabel("Name");
		JPanel namePanel = new JPanel(new FlowLayout());
		namePanel.add(employeeAddingNameLabel);
		
		employeeAddingSalaryField = new JTextField();
		JLabel employeeAddingSalaryLabel = new JLabel("Salary");
		JPanel salaryPanel = new JPanel(new FlowLayout());
		salaryPanel.add(employeeAddingSalaryLabel);
		
		employeeAddingTelephoneField = new JTextField();
		JLabel employeeAddingTelephoneLabel = new JLabel("Telephone");
		JPanel telephonePanel = new JPanel (new FlowLayout());
		telephonePanel.add(employeeAddingTelephoneLabel);
		
		employeeAddingPositionField = new JTextField();
		JLabel employeeAddingPositionLabel = new JLabel("Position");
		JPanel positionPanel = new JPanel(new FlowLayout());
		positionPanel.add(employeeAddingPositionLabel);
		
		//WE MIGHT CHANGE THIS FOR A LIST OF WAREHOUSES YOU CAN SELECT FROM ((might be good to create a method for this, as it might be used more than once))
		employeeAddingWarehouseIDField = new JTextField();
		JLabel employeeAddingWarehouseIDLabel = new JLabel("Warehouse");
		JPanel warehouseIDPanel = new JPanel(new FlowLayout());
		warehouseIDPanel.add(employeeAddingWarehouseIDLabel);
		
		employeeAddingPictureButton = new JButton("Select...");
		employeeAddingPictureBesideLabel = new JLabel("No Archive Selected");
		JLabel employeeAddingPictureLabel = new JLabel ("Picture");
		JPanel picturePanel = new JPanel (new FlowLayout());
		picturePanel.add(employeeAddingPictureLabel);
		
		centerPanel.add(picturePanel);
		JPanel aux = new JPanel(new GridLayout(1, 2, 10, 0));
		aux.add(employeeAddingPictureBesideLabel);
		aux.add(employeeAddingPictureButton);
		centerPanel.add(aux);
		centerPanel.add(namePanel);
		centerPanel.add(employeeAddingNameField);
		centerPanel.add(telephonePanel);
		centerPanel.add(employeeAddingTelephoneField);
		centerPanel.add(positionPanel);
		centerPanel.add(employeeAddingPositionField);
		centerPanel.add(salaryPanel);
		centerPanel.add(employeeAddingSalaryField);
		centerPanel.add(warehouseIDPanel);
		centerPanel.add(employeeAddingWarehouseIDField);
		
		employeeCreationPanel.add(centerPanel, BorderLayout.CENTER);
		
		return employeeCreationPanel;
	}
	
}
