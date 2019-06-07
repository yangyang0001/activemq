package com.inspur.jms.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * User: YANG
 * Date: 2019/6/6-14:24
 * Description: No Description
 */
public class JMSSender {

    //brokerURL : broker(启动的实例就是borker 经纪人的意思)
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory  = new ActiveMQConnectionFactory("tcp://192.168.120.110:61616");
        //这是 异步发送 消息(并且消息是持久化消息的时候才配置,因为非持久化消息默认的就是异步发送的)
//        connectionFactory.setUseAsyncSend(true);
        //创建链接,和broker 链接的
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        //创建队列目的地
        Destination destination = session.createQueue("first-queue?consumer.");

        //创建发送者
        MessageProducer producer = session.createProducer(destination);

        for(int i = 0; i < 10; i++){
            //创建消息
            TextMessage message = session.createTextMessage("Hello Yang" + i);
            //设置消息的持久化和非持久化的 模式(默认的消息就是持久化消息)
//        message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
            //发送消息
            producer.send(message);
            session.commit();
        }


//        session.close();
//        connection.close();
    }
}
