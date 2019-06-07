package com.inspur.jms.nio_protocol;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * User: YANG
 * Date: 2019/6/7-19:27
 * Description: No Description
 */
public class JMSNIOReceiver {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("nio://192.168.120.110:61618");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("nio_queue");
        MessageConsumer consumer = session.createConsumer(destination);

        TextMessage textMessage = (TextMessage) consumer.receive();
        System.out.println(textMessage.getText());

        session.commit();
        session.close();
        connection.close();

    }
}
