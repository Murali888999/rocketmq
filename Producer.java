package rocketmessage;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
public class Producer 
{
	public static void main(String[] args) throws Exception {
      
        DefaultMQProducer producer = new DefaultMQProducer("Producer1");
      producer.setNamesrvAddr("192.168.1.27:9876");        
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        for (int i = 0; i < 100; i++) {           
            Message msg = new Message("TopicTest1",
                "TagA" ,
                ("Hello RocketMQ " +
                    i).getBytes(RemotingHelper.DEFAULT_CHARSET) 
            );            
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }        
       producer.shutdown();
    }
}