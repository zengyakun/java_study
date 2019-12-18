package cc.eslink.spring;

/**
 *@ClassName MessageServiceImpl
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/5/28 13:54
 *@Version 1.0
 **/
public class MessageServiceImpl implements MessageService {

    @Override
    public String getMessage() {
        return "hello, eslink";
    }
}
