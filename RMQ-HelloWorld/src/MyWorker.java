import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;


public class MyWorker {
	
	private final static String TASK_QUEUE_NAME = "task_queue";

	public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
//		//Decalre a queue to consume/receive messages
//		channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);

//Durability		
		boolean durable = true;
		channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);
		//This queueDeclare change needs to be applied to both the producer and consumer code.
		
//Fair dispatch
		//don't dispatch a new message to a worker until it has processed and acknowledged the previous one. Instead, it will dispatch it to the next worker that is not still busy.
		int prefetchCount = 1;
		channel.basicQos(prefetchCount);
				
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		//Declaring a queue is idempotent - it will only be created if it doesn't exist already. 
		//The message content is a byte array, so you can encode whatever you like there.

		final Consumer consumer = new DefaultConsumer(channel) {
			  @Override
			  public void handleDelivery(String consumerTag, Envelope envelope,
					  AMQP.BasicProperties properties, byte[] body) throws IOException {
			    String message = new String(body, "UTF-8");

			    System.out.println(" [x] Received '" + message + "'");
			    try {
					doWork(message);
			    } finally {
			      System.out.println(" [x] Done");

//Message acknowledgment			      
			      channel.basicAck(envelope.getDeliveryTag(), false);  //for actual acknowledgement
			    }
			  }
			};
			boolean autoAck = false; // //disable autoAcknowledgement
			channel.basicConsume(TASK_QUEUE_NAME, autoAck, consumer);
			
			//In order to debug this kind of mistake you can use rabbitmqctl to print the messages_unacknowledged field:
			//rabbitmqctl.bat list_queues name messages_ready messages_unacknowledged
	}
	
	private static void doWork(String task) {
		for (char ch : task.toCharArray()) {
			if (ch == '.') {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException _ignored) {
					Thread.currentThread().interrupt();
				}
			}
		}
	} 
}