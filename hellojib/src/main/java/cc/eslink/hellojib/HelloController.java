package cc.eslink.hellojib;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *@ClassName HelloController
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/11/1 17:40
 *@Version 1.0
 **/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello, " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }

}
