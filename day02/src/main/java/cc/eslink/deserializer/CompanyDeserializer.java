package cc.eslink.deserializer;

import cc.eslink.dto.Company;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 *@ClassName CompanyDeserializer
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/7/17 10:51
 *@Version 1.0
 **/
public class CompanyDeserializer implements Deserializer<Company> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Company deserialize(String topic, byte[] data) {
        if (null == data) {
            return null;
        }
        Schema<Company> schema = RuntimeSchema.getSchema(Company.class);
        Company ans = new Company();
        ProtostuffIOUtil.mergeFrom(data, ans, schema);
        return ans;
    }

    @Override
    public void close() {

    }
}
