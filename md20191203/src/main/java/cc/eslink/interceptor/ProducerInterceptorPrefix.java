package cc.eslink.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 *@ClassName ProducerInterceptorPrefix
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/7/16 17:11
 *@Version 1.0
 **/
public class ProducerInterceptorPrefix implements ProducerInterceptor<String, String> {

    private volatile long sendSuccess = 0;
    private volatile long sendFailure = 0;


    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String modifiedValue = "prefix1-" + record.value();
        return new ProducerRecord<>(record.topic(), record.partition(), record.timestamp(), record.key(), modifiedValue, record.headers());
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception e) {
        if (e == null) {
            sendSuccess++;
        } else {
            sendFailure++;
        }
    }

    @Override
    public void close() {
        double succeRatio = (double)sendSuccess / (sendSuccess + sendFailure);
        System.out.println("[INFO]发送成功率=" + String.format("%f", succeRatio * 100) + "%");
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
