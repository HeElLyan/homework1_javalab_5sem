package ru.he.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ru.he.enums.PdfType;
import ru.he.services.PdfService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class AcademicConsumer {
    private final static String EXCHANGE_NAME = "homework1";
    private final static String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args) {
        PdfService pdfService = new PdfService();
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            // создаем временную очередь со случайным названием
            String queue = channel.queueDeclare().getQueue();
            // привязали очередь к EXCHANGE_NAME
            channel.queueBind(queue, EXCHANGE_NAME, "");

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    pdfService.createPdf(message.getBody(), PdfType.ACADEMIC_VACATION);
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("YOU ARE FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            };
            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
