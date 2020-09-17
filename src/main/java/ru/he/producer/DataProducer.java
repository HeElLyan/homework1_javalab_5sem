package ru.he.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.he.services.ReaderService;

import java.io.*;
import java.util.concurrent.TimeoutException;

public class DataProducer {
    private final static String EXCHANGE_NAME = "homework1";
    private final static String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        ReaderService readerService = new ReaderService();

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            do {
                byte[] data = readerService.readClient();
                channel.basicPublish(EXCHANGE_NAME, "", null, data);
            } while ((reader.readLine()) != null);
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
