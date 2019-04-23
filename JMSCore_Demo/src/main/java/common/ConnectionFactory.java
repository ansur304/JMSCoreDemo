package common;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public interface ConnectionFactory {

	public default Session createSession(Connection connection) {
		Session session = null;
		try {
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		return session;
	}
	
	public default Connection createConnection() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://WL11548:61616");
		Connection connection = null;
		try {
			connection = factory.createConnection();
			connection.start();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public default void destroy(Session session, Connection connection) throws JMSException{
		session.close();
		connection.close();
	}
}
