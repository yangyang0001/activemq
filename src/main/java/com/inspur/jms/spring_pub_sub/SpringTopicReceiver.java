package com.inspur.jms.spring_pub_sub;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/**
 * User: YANG
 * Date: 2019/6/7-15:42
 * Description: No Description
 */
public class SpringTopicReceiver {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-jms.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTopicTemplate");

        while(true){
            String message = (String) jmsTemplate.receiveAndConvert();
            System.out.println("message:-------->" + message);
        }
    }

}
