package com.ann.Client;

import javax.swing.*;

import com.ann.Message;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

public class ClientMain extends JPanel {
    //region server connection elements
    private final String adress = "233.0.0.1";
    private final int port = 1502;
    private ClientListener listener;
    private ClientSender sender;

    //endregion


    //region gui components
    private JPanel messagesPanel;
    private JPanel operationsPanel;
    private JButton sendBtn;
    private JButton clearBtn;
    private JTextField messageTxtField;
    private JTextField userName;
    //endregion


    public ClientMain(String user){
        Initialize();sender = new ClientSender();
        listener = new ClientListener(adress,port,this);
    }

    private void Initialize(){

        setPreferredSize(new Dimension(640,480));
        //region messagesPanel setup
        messagesPanel = new JPanel(new GridLayout(0,1));
        JScrollPane pane = new JScrollPane(messagesPanel);
        pane.setPreferredSize(new Dimension(640,320));
        //endregion
        //region operationsPanel setup
        operationsPanel = new JPanel(new GridLayout(0,1));
        operationsPanel.setPreferredSize(new Dimension(640,140));
        sendBtn = new JButton("Send");
        clearBtn = new JButton("Clear");
        messageTxtField = new JFormattedTextField("Enter your message");
        operationsPanel.add(messageTxtField);
        userName = new JFormattedTextField();
        operationsPanel.add(userName);
        operationsPanel.add(sendBtn);
        operationsPanel.add(clearBtn);
        //endregion



        //add(messagesPanel);
        add(pane);
        add(operationsPanel);


        sendBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(messageTxtField.getText(), Calendar.getInstance().getTime(),userName.getText());
                sender.sendMessage(msg,"233.0.0.1",1502);
                AddMessageToPanel(msg);
            }
        });
    }

    private void AddNewMessages(ArrayList<Message> msgs){
        for (Message item:msgs) {
            if(!item.getSenderName().equals(userName.getText())){
                AddMessageToPanel(item);
            }
        }


    }
    private void AddMessageToPanel(Message item){
        JPanel msgPanel =  new JPanel();
        msgPanel.setPreferredSize(new Dimension(640,65));
        JLabel areaUser = new JLabel(item.getSenderName());
        areaUser.setSize(25,25);
        JLabel areaDate = new JLabel(item.getDate().toString());
        areaDate.setSize(25,25);
        JLabel areaMessage = new JLabel(item.getMsgText());
        areaMessage.setSize(25,25);
        msgPanel.add(areaUser);
        msgPanel.add(areaDate);
        msgPanel.add(areaMessage);
        messagesPanel.add(msgPanel);
        messagesPanel.revalidate();
    }
    public synchronized void receiveMessages(ArrayList<Message> msgs){
        System.out.println("received");
        AddNewMessages(msgs);
    }

    private  void dummy() throws InterruptedException {
        ClientSender sender = new ClientSender();
        for(int i =0;i<25;i++){
            sender.sendMessage(new Message("testText"+i, Calendar.getInstance().getTime(),"user"+i),adress,port);
            System.out.println("sent");
        }
    }




    private static void createAndShowGUI() throws Exception {
        //Create and set up the window.
        JFrame frame = new JFrame("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(640, 480, 640, 480);
        //Create and set up the content pane.
        ClientMain newContentPane = new ClientMain("ann");
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }



    public static void main(String[] args) throws InterruptedException {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                try {
                    createAndShowGUI();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}
