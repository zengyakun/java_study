package cc.eslink.producer;

import cc.eslink.interceptor.ProducerInterceptorPrefix;
import cc.eslink.interceptor.ProducerInterceptorPrefixPlus;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 *@ClassName KafkaProducerAnalysis
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/7/16 16:05
 *@Version 1.0
 **/
public class KafkaProducerAnalysis {

    public static final String brokerList = "server-0:9092";
    public static final String topic = "topic-demo";

    public static Properties initConfig() {
        Properties props = new Properties();
//        props.put("bootstrap.servers", brokerList);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", StringSerializer.class.getName());
        props.put("client.id", "producer.client.id.demo");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerInterceptorPrefix.class.getName() + "," + ProducerInterceptorPrefixPlus.class.getName());
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        int loop = 10;
        while (loop-- > 0) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello,kafka");
            try {
                producer.send(record);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        producer.close();
    }
}
