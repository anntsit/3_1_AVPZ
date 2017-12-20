package com.ann.Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import com.ann.Message;

public class ServerUDP {

    //private BufferedReader in = null;
    //private String str = null;
    private byte[] buffer;
    private DatagramPacket packet;
    private InetAddress address;
    private DatagramSocket socket;

    public ServerUDP() throws IOException {
        //System.out.println("Sending messages");

// Создается объект DatagramSocket, чтобы
// принимать запросы клиента
//        socket = new DatagramSocket();

// Вызов метода transmit(), чтобы передавать сообщение всем
// клиентам, зарегистрированным в группе
//        transmit();


    }

    public void transmit(ArrayList<Message> messages) {
        try {
            socket = new DatagramSocket();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = null;
            try {
                out = new ObjectOutputStream(bos);
                out.writeObject(messages);
                out.flush();
                buffer = bos.toByteArray();

            } finally {
                try {
                    bos.close();
                } catch (IOException ex) {
                    // ignore close exception
                }
            }
                address = InetAddress.getByName("233.0.0.1");
                // Посылка пакета датаграмм на порт номер 1502
                packet = new DatagramPacket(
                        buffer,
                        buffer.length,
                        address,
                        1502);

                //Посылка сообщений всем клиентам в группе
                socket.send(packet);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Закрытие потока и сокета
                //in.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
