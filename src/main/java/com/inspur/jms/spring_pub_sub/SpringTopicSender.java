package com.inspur.jms.spring_pub_sub;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * User: YANG
 * Date: 2019/6/7-15:42
 * Description: No Description
 */
public class SpringTopicSender {

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-jms.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTopicTemplate");

        for(int i = 0; i < 10; i++){
            int finalI = i;
            jmsTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage("Hello world" + finalI);
                }
            });
//            Thread.sleep(100);
        }

    }
}
