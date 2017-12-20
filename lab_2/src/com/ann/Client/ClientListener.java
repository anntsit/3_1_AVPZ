package com.ann.Client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

import com.ann.Message;

public class ClientListener extends Thread {
    Thread runner;
    private ClientMain mainClass;
    private InetAddress address;
    private byte[] buffer;
    private DatagramPacket packet;
    private String str;
    private MulticastSocket socket;


    public ClientListener(String adress, int port,ClientMain client){
        mainClass=client;
        runner = new Thread(this);
        start();
    }

    public void run(){
        try {

            // Создание объекта MulticastSocket, чтобы получать
// данные от группы, используя номер порта 1502
            socket = new MulticastSocket(1502);

            address = InetAddress.getByName("233.0.0.1");

            // Регистрация клиента в группе
            socket.joinGroup(address);
            while (true) {
                buffer = new byte[socket.getReceiveBufferSize()];
                packet = new DatagramPacket(
                        buffer, buffer.length);
                System.out.println("get");
                // Получение данных от сервера
                socket.receive(packet);

                ArrayList<Message> msgs = convertFromByteArr(packet.getData());
                System.out.println(msgs.size());
                synchronized (mainClass){
                    mainClass.receiveMessages(msgs);
                }
                System.out.println(msgs.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Удаление клиента из группы
                socket.leaveGroup(address);

                // Закрытие сокета
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    private ArrayList<Message> convertFromByteArr(byte[] arr){
        ByteArrayInputStream bis = new ByteArrayInputStream(arr);
        ArrayList<Message> o=null;
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            o = (ArrayList<Message>) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        return o;
    }

}
