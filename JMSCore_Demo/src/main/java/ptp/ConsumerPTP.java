package ptp;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.util.Assert;

import common.ConnectionFactory;

public class ConsumerPTP implements ConnectionFactory {

	public void consumeMsg() {
		
		
		try {
			Connection connection = createConnection();
			Assert.notNull(connection, "connection is NULL");
			Session session = createSession(connection);
			Assert.notNull(session, "session is NULL");
			Destination source = session.createQueue("TestJMSQueue2");
			MessageConsumer consumer = session.createConsumer(source);
			Message msg = consumer.receive(1000);
			
			if(msg instanceof TextMessage) {
				System.out.println("Message Received Successfully at "+new Date()+", Text :: "+((TextMessage) msg).getText());
			} else {
				System.out.println("Message is not in expected format");
			}
			
			destroy(session, connection);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
