package com.inspur.jms.pub_sub;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * User: YANG
 * Date: 2019/6/6-16:45
 * Description: No Description
 */
public class JMSTopicReceiver2 {

    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.120.110:61616");
        Connection connection = connectionFactory.createConnection();
        //客户端 设置持久性订阅的第一处 代码
        String clientID = "Client0002";
        connection.setClientID(clientID);
        connection.start();

        Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);

        Topic topic = session.createTopic("second-topic");
        //客户端设置 持久性订阅的第二处代码
        MessageConsumer consumer = session.createDurableSubscriber(topic, clientID);

//        MessageConsumer consumer = session.createConsumer(topic);

        for(int i = 0; i < 10; i++){
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println(message.getText());
            message.acknowledge();
        }

        session.close();
        connection.close();



    }
}
