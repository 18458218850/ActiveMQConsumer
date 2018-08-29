package com.zhuokai.service;

import java.io.IOException;
import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import com.zhuokai.model.User;

@Service
public class MsgTestService {

	
	//@JmsListener JMS消息监听器 一对一模式
	@JmsListener(destination="myQueue")
	public void receiveQueueMag(User u) throws JMSException, IOException{
		System.out.println("接收消息 "+u.getName());
		
		/*//完整的过程
		//1、创建工厂连接对象，需要制定ip和端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //2、使用连接工厂创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //3、开启连接
        connection.start();
        //4、使用连接对象创建会话（session）对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Queue queue = session.createQueue("test-queue");
        //6、使用会话对象创建生产者对象
        MessageConsumer consumer = session.createConsumer(queue);
        //7、向consumer对象中设置一个messageListener对象，用来接收消息
        consumer.setMessageListener(new MessageListener() {
 
            @Override
            public void onMessage(Message message) {
                // TODO Auto-generated method stub
                if(message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage)message;
                    try {
                        System.out.println(textMessage.getText());
                    } catch (JMSException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        //8、程序等待接收用户消息
        System.in.read();
        //9、关闭资源
        consumer.close();
        session.close();
        connection.close();*/
   
	}
	
	//加上containerFactory="myCont" 变成订阅模式
	@JmsListener(destination="myTopic",containerFactory="myCont")
	
	public void receiveTopicMsg(Message m){
		if(m instanceof ObjectMessage){ 
			ObjectMessage objMessage = (ObjectMessage) m;  
            try {  
            	User u = (User) objMessage.getObject();
                System.out.println("接收到一个ObjectMessage，包含User对象。");  
                System.out.println(u.getName());  
            } catch (JMSException e) {  
                e.printStackTrace();  
            } 
		}
	}
}
