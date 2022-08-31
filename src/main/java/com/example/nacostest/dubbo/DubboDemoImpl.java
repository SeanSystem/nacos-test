package com.example.nacostest.dubbo;


import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author huyb
 * @date 2022-08-31 9:21
 */
@DubboService
public class DubboDemoImpl implements DubboDemo {

    @Override
    public void sayHello(String name) {
        System.out.println("hello:" + name);
    }
}
