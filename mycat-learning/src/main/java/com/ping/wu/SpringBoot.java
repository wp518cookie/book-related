package com.ping.wu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author wuping
 * @date 2018/7/11
 */

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class SpringBoot {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot.class);
    }
}
