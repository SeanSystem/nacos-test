package com.example.nacostest;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosConfigurationProperties(dataId = "test", autoRefreshed = true)
@EnableDubbo
public class NacosTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosTestApplication.class, args);
    }

}
