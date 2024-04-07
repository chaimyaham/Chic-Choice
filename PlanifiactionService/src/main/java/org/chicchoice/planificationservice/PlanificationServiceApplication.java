package org.chicchoice.planificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.simplon"
)
public class PlanificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanificationServiceApplication.class, args);
    }

}
