package cc.eslink.test;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.junit.Test;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 *@ClassName KafkaAdminClientTest
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/7/18 16:02
 *@Version 1.0
 **/
public class KafkaAdminClientTest {

    @Test
    public void describeTopic() {
        String[] options = new String[]{
                "--zookeeper", "server-0:2181", "--describe", "--topic", "topic-create"
        };
        kafka.admin.TopicCommand.main(options);
    }

    @Test
    public void createTopic() {
        String brokerList = "server-0:9092";
        String topic = "topic-admin";
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        AdminClient client = AdminClient.create(props);

        NewTopic newTopic = new NewTopic(topic, 4, (short) 1);
        CreateTopicsResult result = client.createTopics(Collections.singleton(newTopic));
        try {
            result.all().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
