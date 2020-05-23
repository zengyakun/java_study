package cc.eslink.fundanalyze;

import cc.eslink.fundanalyze.entity.User;
import cc.eslink.fundanalyze.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan("cc.eslink.fundanalyze.mapper")
@SpringBootTest
class FundAnalyzeApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        User user = userService.Sel(1);
        System.out.println(user);
    }

}
