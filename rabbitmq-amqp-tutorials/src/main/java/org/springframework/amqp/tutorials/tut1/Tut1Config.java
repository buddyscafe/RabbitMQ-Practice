package org.springframework.amqp.tutorials.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut1","hello-world"})
@Configuration
public class Tut1Config {

    //we've defined the 1st tutorial profile as either tut1, the package name, or hello-world. We use the @Configuration to let Spring know that this is a Java Configuration and in it we create the definition for our Queue ("hello") and define our Sender and Receiver beans.

    @Bean
    public Queue hello() {
        return new Queue("hello5");
    }

    @Profile("receiver")
    @Bean
    public Tut1Receiver receiver() {
        return new Tut1Receiver();
    }

    @Profile("sender")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender();
    }
}
