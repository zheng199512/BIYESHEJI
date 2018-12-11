package cn.e3mall.activemq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ActiveMqSpringTest {
	@Test
	public void testSpringActiveMq(){
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		JmsTemplate jmsTemplate=applicationContext.getBean(JmsTemplate.class);
		Destination destination=(Destination) applicationContext.getBean("queueDestination");
		jmsTemplate.send(destination,new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message=session.createTextMessage("zhenghongfei-spring");
				return message;
			}
		});
	}
	@Test
	public void testQueueProducer(){
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		JmsTemplate jmsTemplate=applicationContext.getBean(JmsTemplate.class);
		Queue queue=(Queue) applicationContext.getBean("queueDestination");
		jmsTemplate.send(queue,new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage=session.createTextMessage("11郑鸿飞");
				return textMessage;
			}
		});
	}
	
	
}
