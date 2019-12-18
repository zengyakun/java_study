package com.eslink.test06;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Random;

/**
 *@ClassName TestController
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/11/18 10:26
 *@Version 1.0
 **/
@RestController
public class TestController {

    @Resource
    private DruidDataSource dataSource;

    @RequestMapping("/save")
    public String save() throws Exception {
        Connection conn = dataSource.getConnection();
        String sql = "INSERT INTO `test`.`test`(`id`, `value`) VALUES (?, ?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, new Random().nextInt());
        st.setString(2, "看\uD84C\uDFB4清");
        st.execute();
        st.executeUpdate();
        return "";
    }

//    public DataSource getDataSource() throws Exception {
//        Properties props = new Properties();
//        props.put("driverClassName", "com.mysql.jdbc.Driver");
//        props.put("url", "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8");
//        props.put("username", "root");
//        props.put("password", "123456");
//        props.put("initConnectionSqls", "set names utf8mb4;");
//        return DruidDataSourceFactory.createDataSource(props);
//    }
}
