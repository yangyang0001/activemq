package com.inspur.jms.spring_ptp;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/**
 * User: YANG
 * Date: 2019/6/7-1:54
 * Description: No Description
 */
public class SpringJMSReceiver {

    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-jms.xml");

        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsQueueTemplate");

        while(true){
            String message = (String) jmsTemplate.receiveAndConvert();
            System.out.println("message:-------->" + message);
        }

    }
}
