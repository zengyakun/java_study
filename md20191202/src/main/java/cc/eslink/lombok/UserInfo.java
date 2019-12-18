package cc.eslink.lombok;

import lombok.Builder;
import lombok.Getter;

/**
 *@ClassName UserInfo
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/5/27 17:58
 *@Version 1.0
 **/
@Builder(toBuilder = true)
@Getter
public class UserInfo {
    private String name;
    private String email;
}
