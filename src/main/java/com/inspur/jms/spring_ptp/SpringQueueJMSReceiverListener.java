package com.inspur.jms.spring_ptp;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * User: YANG
 * Date: 2019/6/7-2:01
 * Description: No Description
 */
public class SpringQueueJMSReceiverListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("listener message is :" + ((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
