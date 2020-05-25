package cc.eslink.fundanalyze.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *@ClassName StringUtil
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2020/5/25 0:28
 *@Version 1.0
 **/
public class StringUtil {

    public static String getNumberDefault(String text) {
        return StringUtils.defaultIfBlank(getNumber(text), "0");
    }

    public static String getNumber(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        Pattern p = Pattern.compile("^(-)?(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?");
        Matcher m = p.matcher(text);
        if (m.find()) {
            return m.group(0);
        }
        return null;
    }
}
