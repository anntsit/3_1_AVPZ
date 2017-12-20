package avps.labs;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JLabel lbDate = new JLabel("������� ����: ");
	private JTextField txtDate = new JTextField();
	private JButton btnDate= new JButton("�����");
	private JLabel lbMeetings = new JLabel();
	
	public static void main(String[] params) {
		new Main();
	}

	public Main() {
		this.setTitle("����� ������ �� ����");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(500, 350);

		// ���������� ��������� ���������� � ������
		JPanel inputPanel = new JPanel(new GridLayout(1, 3));
		inputPanel.add(lbDate);
		inputPanel.add(txtDate);
		inputPanel.add(btnDate);
		JPanel tablePanel = new JPanel(new GridLayout(1, 1));
		lbMeetings.setHorizontalAlignment(JLabel.CENTER);
		tablePanel.add(lbMeetings);
		
		this.add(inputPanel, BorderLayout.NORTH);
		this.add(tablePanel, BorderLayout.CENTER);
		
		btnDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchMeetings();
			}			
		});
		
		// ���������� ������ �� ������
		this.setVisible(true);
	}
	
	private void searchMeetings() { 
		try {
			new TransformMeetingsToHTML(txtDate.getText()); //"12.12.2017"
			
			BufferedReader reader = new BufferedReader(new FileReader("meetings.html"));
			String line;
			ArrayList<String> listOfStrings = new ArrayList<>();
			do {
				line = reader.readLine();
				if (line == null)
					break;
				
				listOfStrings.add(line);
			}
			while(true);
			
			lbMeetings.setText("");
			StringBuffer result = new StringBuffer();
			for (String element: listOfStrings)
				result.append(element);
			if (listOfStrings.size() == 0 || numberOfSubstringOccurences(result.toString(), "<tr>") < 1)
				lbMeetings.setText("�� ������ ���� �� ������� ������.");
			else
				lbMeetings.setText(result.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private int numberOfSubstringOccurences(String initString, String substring) {
		int lastIndex = 0;
		int count = 0;

		while(lastIndex != -1) {
		    lastIndex = initString.indexOf(substring, lastIndex);

		    if(lastIndex != -1) {
		        count ++;
		        lastIndex += substring.length();
		    }
		}
		return count;
	}
}
