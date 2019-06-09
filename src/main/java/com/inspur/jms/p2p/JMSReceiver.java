package com.inspur.jms.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * User: YANG
 * Date: 2019/6/6-14:39
 * Description: No Description
 * 这里的会话模式分为:事务性会话和非事务性会话
 * 事务性会话:
 *      createSession(true, auto_ack);//如果是事务性会话 第二个参数设置为auto_ack 在消费完成后 session.commit()就OK了
 *
 *
 * 非事务性会话的向ActiveMQ发送ACK,进行确认签收的过程是分策略的:由createSession()的第二个参数进行决策的!
 *      既然是非事务性的会话,则第一个参数就是false,这样第二个参数就起作用了!
 *  1.auto_ack
 *  2.client_ack
 *  3.dups_ack
 *
 * 特别注意:(本人亲测)
 * 在PTP的模式中可以有多个消费者,但是每条消息只能被一个消费者接受而已!
 *
 */
public class JMSReceiver {

    public static void main(String[] args) throws JMSException {
        //采用容错机制 failover 配置
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover:(tcp://192.168.120.110:61616,tcp://192.168.120.150:61616,tcp://192.168.120.224:61616)");
        //创建链接,和broker 链接的
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        //创建队列目的地
        Destination destination = session.createQueue("first-queue");

        //创建消费者
        MessageConsumer consumer = session.createConsumer(destination);

        //消费端接收消息
        for(int i = 0; i < 5; i++){
//        while(true){
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println("消费者消费信息:" + message.getText());
//            message.acknowledge();
            session.commit();
//        session.commit();
        }

//        session.close();
//        connection.close();

    }
}
