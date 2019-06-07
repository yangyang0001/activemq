package com.inspur.jms.pub_sub;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * User: YANG
 * Date: 2019/6/6-16:37
 * Description: No Description
 */
public class JMSTopicSender {

    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.120.110:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("second-topic");

        MessageProducer producer = session.createProducer(topic);

        for(int i = 0; i < 10; i++){
            TextMessage message = session.createTextMessage("Hello topic" + i);
            producer.send(message);
            session.commit();
        }

        session.close();
        connection.close();

    }
}
