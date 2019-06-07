package com.inspur.jms.spring_ptp;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * User: YANG
 * Date: 2019/6/7-1:20
 * Description: No Description
 */
public class SpringJMSSender {

    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-jms.xml");

        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsQueueTemplate");

        for(int i = 0; i < 20; i++){
            int finalI = i;
            jmsTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage("Hello world" + finalI);
                }
            });
        }


        System.out.println("--------------------------------------------------");

    }
}
