package cc.eslink.spring;

import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.ParseException;

import java.util.Date;

/**
 *@ClassName StringToDateConverter
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/5/30 17:02
 *@Version 1.0
 **/
public class StringToDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        try {
//            return DateUtils.parseDate(source, "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "HH:mm:ss", "HH:mm");
        } catch (ParseException e) {
            return null;
        }
        return null;
    }
}
