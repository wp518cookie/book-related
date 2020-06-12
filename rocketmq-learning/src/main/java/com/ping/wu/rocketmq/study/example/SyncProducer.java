package com.ping.wu.rocketmq.study.example;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author wuping
 * @date 2020-04-19
 */

public class SyncProducer {
    public static void main(String[] args) throws Exception {
//        DefaultMQProducer producer = new DefaultMQProducer("syncproducer_group");
//
//        producer.setNamesrvAddr("134.175.57.59:9876");
//        producer.start();
//        for (int i = 0; i < 5; i++) {
//            Message msg = new Message("TopicTest123", "TagA", "SyncProducer:Hello RocketMQ".getBytes(RemotingHelper.DEFAULT_CHARSET));
//            SendResult sendResult = producer.send(msg);
//            System.out.printf("%s%n", sendResult);
//        }
//        producer.shutdown();

        DefaultMQProducer producer = new DefaultMQProducer("rocketmq_demo");
        producer.setNamesrvAddr("134.175.57.59:9876");
        producer.start();
        for (int i = 0; i < 1; i++) {
            Message msg = new Message("TopicTest", "TagA", ("Hello RocketMq " + i)
                    .getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
        }
        producer.shutdown();
    }
}
