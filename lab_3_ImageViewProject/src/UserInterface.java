import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class UserInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JList listOfImages;
	private JTextArea shortDescriptionTextArea;
	private JTextArea dateOfCreationTextArea;
	private JTextArea authorTextArea;
	private JTextArea fileNameTextArea;
	private JLabel imageLabel;
	
	
	// Initializing user interface
	public UserInterface() {
		
//		ImageServer server = new ImageServer();
//		byte[] result = new byte[] {};
//		try {
// 			//result = server.getFileContent("skype.png");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		this.setTitle("Перегляд зображень");
		this.setSize(new Dimension(675, 500));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);  
		this.setResizable(false);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		JPanel listOfImagesPanel = new JPanel(new GridBagLayout());		
		JPanel imageViewPanel = new JPanel(new BorderLayout());
			
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.weightx = 2;
		gridBagConstraints.weighty = 1;
		mainPanel.add(listOfImagesPanel, gridBagConstraints);

		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.weightx = 3;
		gridBagConstraints.weighty = 1;
		gridBagConstraints.insets = new Insets(0, 10, 0, 0);
		mainPanel.add(imageViewPanel, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;	
		gridBagConstraints.weighty = 0;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.weightx = 1;
		JLabel label = new JLabel("Список зображень сервера:", JLabel.LEFT);
		listOfImagesPanel.add(label, gridBagConstraints);
		
		listOfImages = new JList(); 
		listOfImages.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listOfImages.setLayoutOrientation(JList.VERTICAL);
		listOfImages.setVisibleRowCount(0);
		listOfImages.setBackground(new Color(205, 208, 209));
		
		// Listener of selection changed event
		listOfImages.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg) {
            	onListSelectionChanged(arg);
            }});
		JScrollPane listScroller = new JScrollPane(listOfImages);

		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;	
		gridBagConstraints.weighty = 1;
		listOfImagesPanel.add(listScroller, gridBagConstraints);
			
		JPanel fileInfoPanel = new JPanel(new GridBagLayout());

		shortDescriptionTextArea = new JTextArea(1, 25);  
		shortDescriptionTextArea.setEditable(false);
		shortDescriptionTextArea.setBackground(new Color(205, 208, 209));
		
		dateOfCreationTextArea = new JTextArea(1, 25);  
		dateOfCreationTextArea.setEditable(false);
		dateOfCreationTextArea.setBackground(new Color(205, 208, 209));
		
		authorTextArea = new JTextArea(1, 25);  
		authorTextArea.setEditable(false);
		authorTextArea.setBackground(new Color(205, 208, 209));
		
		fileNameTextArea = new JTextArea(1, 25);  
		fileNameTextArea.setEditable(false);
		fileNameTextArea.setBackground(new Color(205, 208, 209));
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;	
		gridBagConstraints.weighty = 1;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.insets = new Insets(0, 0, 0, 0);
		fileInfoPanel.add(new JLabel("Назва файлу: "), gridBagConstraints);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.insets = new Insets(5, 0, 5, 0);
		fileInfoPanel.add(new JLabel("Автор файлу: "), gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 10;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(0, 0, 0, 0);
		fileInfoPanel.add(fileNameTextArea, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 10;
		gridBagConstraints.insets = new Insets(5, 0, 5, 0);
		fileInfoPanel.add(authorTextArea, gridBagConstraints);
			
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.insets = new Insets(0, 10, 0, 0);
		fileInfoPanel.add(new JLabel("Короткий опис: "), gridBagConstraints);
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.insets = new Insets(5, 10, 5, 0);
		fileInfoPanel.add(new JLabel("Дата створення: "), gridBagConstraints);
		
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 10;
		gridBagConstraints.insets = new Insets(0, 0, 0, 0);
		fileInfoPanel.add(shortDescriptionTextArea, gridBagConstraints);
		
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 10;
		gridBagConstraints.insets = new Insets(5, 0, 5, 0);
		fileInfoPanel.add(dateOfCreationTextArea, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;	
		gridBagConstraints.weighty = 1;
		gridBagConstraints.weighty = 1;
		imageViewPanel.add(fileInfoPanel, BorderLayout.NORTH);
		
		imageLabel = new JLabel();
		Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
		imageLabel.setBorder(solidBorder);
		imageLabel.setHorizontalTextPosition(JLabel.CENTER);
		imageLabel.setVerticalTextPosition(JLabel.CENTER);
		imageLabel.setVerticalAlignment(JLabel.CENTER);
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		imageViewPanel.add(imageLabel, BorderLayout.CENTER);
		
        this.getContentPane().add(mainPanel);        
        this.setVisible(true);

        viewDidLoad();   
	}
	
	private void viewDidLoad() {
		loadImagesListFromServer();
	}
	
	private void loadImagesListFromServer() {
		try {
			ImageServerDataProvider server = (ImageServerDataProvider) Naming.lookup("rmi://localhost/ImageServer");
			
			listOfImages.setListData(server.getAllFiles());
			listOfImages.setSelectedIndex(0);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void onListSelectionChanged(ListSelectionEvent arg) {
        if (!arg.getValueIsAdjusting()) {
        	try {
        		String fileName = listOfImages.getSelectedValue().toString();
            	ImageServerDataProvider server = (ImageServerDataProvider) Naming.lookup("rmi://localhost/ImageServer");

            	FileInfo fileInfo = server.getFileInfo(fileName);
            	
            	String description = fileInfo.getFileDescription();
            	description = description.length() > 24 ? description.substring(0, 22) + "..." : description;
            	shortDescriptionTextArea.setText(description);
            	dateOfCreationTextArea.setText(fileInfo.getFileCreationDate());
            	authorTextArea.setText(fileInfo.getFileAuthor());
            	fileNameTextArea.setText(fileInfo.getFilePath());
            	
            	Image image = image = ServerInterop.getImageFromByteArray(fileInfo.getFileContent());
                Image imageInSize = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                imageLabel.setText("");
                imageLabel.setIcon(null);
                imageLabel.setIcon(new ImageIcon(imageInSize));
                imageLabel.setText("");
        	}
        	catch (Exception e) {
        		e.printStackTrace();
        	}
        	        	
        	// JOptionPane.showMessageDialog(null, listOfImages.getSelectedValue().toString());
        }
	}
	
	public static void main(String[] args) {
		new UserInterface();	
	}
}
