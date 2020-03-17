package cc.eslink.jjwtdemo.jwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@ClassName HelloController
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/3/17 13:19
 *@Version 1.0
 **/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello jwt!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "hello admin!";
    }
}
