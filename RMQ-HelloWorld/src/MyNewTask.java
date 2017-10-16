import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class MyNewTask {

	private final static String TASK_QUEUE_NAME = "task_queue";

	public static void main(String[] argv) throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		//Decalre a queue to publish/send messages
//		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

//Durability
		boolean durable = true;
		channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);
		//This queueDeclare change needs to be applied to both the producer and consumer code.
		
		String message = getMessage(argv);
//		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//Persistence of messages
////mark our messages as persistent - by setting MessageProperties (which implements BasicProperties) to the value PERSISTENT_TEXT_PLAIN.
		channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,
	            message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");

		//Declaring a queue is idempotent - it will only be created if it doesn't exist already. 
		//The message content is a byte array, so you can encode whatever you like there.
		
		channel.close();
		connection.close();
	}
	
	private static String getMessage(String[] strings){
	    if (strings.length < 1)
	        return "Hello World!";
	    return joinStrings(strings, " ");
	}

	private static String joinStrings(String[] strings, String delimiter) {
	    int length = strings.length;
	    if (length == 0) return "";
	    StringBuilder words = new StringBuilder(strings[0]);
	    for (int i = 1; i < length; i++) {
	        words.append(delimiter).append(strings[i]);
	    }
	    return words.toString();
	}

}
