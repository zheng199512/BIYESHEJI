package cn.e3mall.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.xml.soap.Text;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;
public class ActiveMqTest {
	@Test
	public void testQueueProducer() throws Exception{
		ConnectionFactory connectionFactory=new org.apache.activemq.ActiveMQConnectionFactory("tcp://192.168.25.129:61616");
		Connection connection=connectionFactory.createConnection();
		connection.start();
		Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue=session.createQueue("spring-queue");
		MessageProducer producer=session.createProducer(queue);
		TextMessage textMessage=session.createTextMessage("hello zheng haha");
		producer.send(textMessage);
		producer.close();
		session.close();
		connection.close();
	}
	@Test
	public void testQueueConsumer() throws Exception{
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.25.129:61616");
		Connection connection=connectionFactory.createConnection();
		connection.start();
		Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue=session.createQueue("test-queue");
		MessageConsumer consumer=session.createConsumer(queue);
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				try {
					TextMessage textMessage=(TextMessage)message;
					String text=null;
					text=textMessage.getText();
					System.out.println(text);
				} catch (JMSException e) {
					e.printStackTrace();
				}
				
			}
		});
		System.in.read();
		consumer.close();
		session.close();
		connection.close();
		
	}
	@Test
	public void testTopicProducer() throws Exception{
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.25.129:61616");
		Connection connection=connectionFactory.createConnection();
		connection.start();
		Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic=session.createTopic("test-topic");
		MessageProducer producer=session.createProducer(topic);
		TextMessage textMessage=session.createTextMessage("hello zhengok");
		producer.send(textMessage);
		System.out.println("ok...");
		producer.close();
		session.close();
		connection.close();
	}
	@Test
	public void testTopicConsumer() throws Exception{
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.25.129:61616");
		Connection connection=connectionFactory.createConnection();
		connection.start();
		Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic=session.createTopic("test-topic");
		MessageConsumer consumer=session.createConsumer(topic);
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				
				try {
					TextMessage textMessage=(TextMessage) message;
					String text=null;
					text=textMessage.getText();
					System.out.println(text);
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("topic:消费...");
		System.in.read();
		consumer.close();
		session.close();
		connection.close();
		
	}
}
