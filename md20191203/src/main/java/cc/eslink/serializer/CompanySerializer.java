package cc.eslink.serializer;

import cc.eslink.dto.Company;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Map;

/**
 *@ClassName CompanySerializer
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/7/17 10:51
 *@Version 1.0
 **/
public class CompanySerializer implements Serializer<Company> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Company data) {
        if (null == data) {
            return null;
        }
        Schema schema = (Schema) RuntimeSchema.getSchema(data.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] protostuff = null;
        try {
            protostuff = ProtostuffIOUtil.toByteArray(data, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
        return protostuff;
    }

    @Override
    public void close() {

    }
}
