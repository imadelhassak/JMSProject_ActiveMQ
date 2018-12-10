package jmsactivemq;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


public class JMS_Consumer {
		
			public static void main(String[] args) throws JMSException, IOException {
				
				  ConnectionFactory connectionFactory = new ActiveMQConnectionFactory
						("tcp://localhost:61616");
				  Connection connection=connectionFactory.createConnection();
				  connection.start();
				  Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
				  Destination destination = session.createQueue("myqueue.queue");
				  MessageConsumer consumer = session.createConsumer(destination);
				  Message message = consumer.receive();
				  BytesMessage bytemsg = (BytesMessage)message;
				  File file = new File("image.jpg");
				  FileOutputStream fos = new FileOutputStream(file);
				  BufferedOutputStream outBuf = new BufferedOutputStream(fos);
				  int i;
				  while((i=bytemsg.readInt())!=-1){
				     outBuf.write(i);
				  }
				  System.out.println("consumer get images");
				  outBuf.close();
				  fos.close();
				  					
			
		}
		}