package com.miao.robot.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RobotEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotEurekaApplication.class, args);
    }
}
