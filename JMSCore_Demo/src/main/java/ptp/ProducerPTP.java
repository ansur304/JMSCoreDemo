package ptp;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.util.Assert;

import common.ConnectionFactory;

public class ProducerPTP implements ConnectionFactory {

	public void produceMsg(String msgTxt) {
		try {
			Connection connection = createConnection();
			Assert.notNull(connection, "connection is NULL");
			Session session = createSession(connection);
			Assert.notNull(session, "session is NULL");
			Destination dest = session.createQueue("TestJMSQueue2");
			
			MessageProducer msgProducer = session.createProducer(dest);
			msgProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			TextMessage message = session.createTextMessage(msgTxt);
			msgProducer.send(message);
			destroy(session, connection);
			
			System.out.println("Message sent Successfully at "+new Date()+", Text :: "+msgTxt);
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
