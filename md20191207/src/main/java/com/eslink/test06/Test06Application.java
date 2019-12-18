package com.eslink.test06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:application-bean.xml"})
public class Test06Application {

    public static void main(String[] args) {
        SpringApplication.run(Test06Application.class, args);
    }

}
