package org.springframework.amqp.tutorials;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = RabbitAmqpTutorialsApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class RabbitAmqpTutorialsApplicationTests {

	@Test
	public void contextLoads() {
	}

}
