package com.inspur.jms.nio_protocol;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * User: YANG
 * Date: 2019/6/7-19:22
 * Description: No Description
 */
public class JMSNIOSender {

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("nio://192.168.120.110:61618");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("nio_queue");
        MessageProducer producer = session.createProducer(destination);

        TextMessage textMessage = session.createTextMessage("nio_test_message");
        producer.send(textMessage);

        session.commit();
        session.close();
        connection.close();
    }
}
