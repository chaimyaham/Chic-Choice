package org.chicchoice.vetementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class VetementServiceApplication
{
    public static void main(String[] args) {
        SpringApplication.run(VetementServiceApplication.class, args);
    }
}
