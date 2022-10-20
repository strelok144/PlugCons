package queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
   private String message = "";

   public String getMessage(){
       return message;
   }
    private final static String QUEUE_NAME = "queueResp";

    public void reading() throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(
                QUEUE_NAME,
                false,
                false,
                false,
                null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Вычитано из очереди: '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
