package com.ann.Server;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.ann.Message;

/**
 * Класс сервера (выполняется в отдельном процессе)
 */
public class ServerTCP extends Thread  {

    ArrayList<Message> messages;
    Timer timer;
    ServerUDP udpSender;
    // Объявляется ссылка
    // на объект - сокет сервера
    ServerSocket serverSocket = null;

    /**
     * Конструктор по умолчанию
     */
    public ServerTCP() {
        try {
            // Создается объект ServerSocket, который получает
            // запросы клиента на порт 1502
            messages= new ArrayList<Message>();
            timer=new Timer();
            udpSender = new ServerUDP();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if(messages.size()!=0){
                    udpSender.transmit(messages);
                    System.out.println("sending");
                    messages.clear();
                    }
                }
            },0,10000
            );
            serverSocket = new ServerSocket(1502);
            System.out.println("Starting the server ");
            // Запускаем процесс
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Запуск процесса
     */
    public void run() {
        try {

            while (true) {
                // Ожидание запросов соединения от клиентов
                Socket clientSocket = serverSocket.accept();

                System.out.println("Acception ");

                // Получение выходного потока,
                // связанного с объектом Socket
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                messages.add((Message)in.readObject());
                System.out.println("received"+messages.get(messages.size()-1));
                // Создание объекта для передачи клиентам
//                DateMessage dateMessage = new DateMessage(
//                        Calendar.getInstance().getTime(),
//                        "Текущая дата/время на сервере");
//                // Запись объекта в выходной поток
//                out.writeObject(dateMessage);
//                out.close();
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        // Запуск сервера
        new ServerTCP();
    }

}
