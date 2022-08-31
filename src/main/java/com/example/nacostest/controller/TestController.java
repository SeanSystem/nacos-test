package com.example.nacostest.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huyb
 * @date 2022-08-30 14:59
 */
@RestController
@RequestMapping("/config")
public class TestController {

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @NacosValue(value = "${name:hello}", autoRefreshed = true)
    private String name;

    @NacosValue(value = "${port:8080}", autoRefreshed = true)
    private Integer port;

    @GetMapping("test")
    public Boolean test() {
        System.out.println(port);
        return useLocalCache;
    }

}
