package cc.eslink.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/**
 *@ClassName KafkaConsumerAnalysis
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/7/17 9:45
 *@Version 1.0
 **/
public class KafkaConsumerAnalysis {

    public static final String brokerList = "server-0:9092";
    public static final String topic = "topic-demo";
    public static final String groupId = "group.demo";
    public static final AtomicBoolean isRunning = new AtomicBoolean(true);

    public static Properties initConfig() {
        Properties props = new Properties();
//        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("bootstrap.servers", brokerList);
        props.put("group.id", groupId);
        props.put("client.id", "consumer.client.id.demo");
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // 方式1
//        consumer.subscribe(Arrays.asList(topic));

        // 方式2
//        consumer.subscribe(Pattern.compile("topic-.*"));

        // 方式3
        List<TopicPartition> partitions = new ArrayList<>();
        List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);
        if (null != partitionInfos) {
            for (PartitionInfo tpInfo : partitionInfos) {
                partitions.add(new TopicPartition(tpInfo.topic(), tpInfo.partition()));
            }
        }
        consumer.assign(partitions);

        consumer.unsubscribe();

        try {
            while (isRunning.get()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMinutes(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("topic=" + record.topic() + ",partition=" + record.partition()
                            + ",offset=" + record.offset());
                    System.out.println("key=" + record.key() + ",value=" + record.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
