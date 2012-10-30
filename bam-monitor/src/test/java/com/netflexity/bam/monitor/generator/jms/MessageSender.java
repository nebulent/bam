package com.netflexity.bam.monitor.generator.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MessageSender {

    private Destination destination;
    private JmsTemplate jmsTemplate;

    public MessageSender() {
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void sendMessage(final String text) {
        MessageCreator creator = new MessageCreator() {

            public Message createMessage(Session session) {
                TextMessage message = null;
                try {
                    message = session.createTextMessage();
                    message.setStringProperty("text", text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return message;
            }
        };

        jmsTemplate.send(destination, creator);
    }
}
