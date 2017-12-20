package com.ann.Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.ann.Message;

public class ClientSender {
    public void sendMessage(Message msg,String adress,int port){
        try {
            Socket clientSocket = new Socket("localhost", port);
            ObjectOutputStream out =
                    new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(msg);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
