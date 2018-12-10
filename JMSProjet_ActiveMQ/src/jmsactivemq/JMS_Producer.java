package jmsactivemq;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMS_Producer {

	public static void main(String[] args) throws IOException {
		
		  try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory
					("tcp://localhost:61616");
			  Connection connection=connectionFactory.createConnection();
			  connection.start();
			  Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			  Destination destination = session.createQueue("myqueue.queue");
			  MessageProducer producer = session.createProducer(destination);
			  producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			  File fileToPublish=new File("C:/img2.jpg");
			  BytesMessage bytemsg = session.createBytesMessage();
			  InputStream in= new FileInputStream(fileToPublish);
			  @SuppressWarnings("resource")
			  BufferedInputStream inBuf= new BufferedInputStream(in);
			  int i;
			  while((i=inBuf.read())!=-1){
				  bytemsg.writeInt(i);
			  }
			  bytemsg.writeInt(-1);
			  producer.send(bytemsg);
	          System.out.println("sent successfully");
	          
		    } catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		   }

}

