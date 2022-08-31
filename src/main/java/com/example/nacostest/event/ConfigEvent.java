package com.example.nacostest.event;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

/**
 * @author huyb
 * @date 2022-08-31 10:00
 */
public class ConfigEvent implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final String NACOS_CONFIG_SERVER_ADDR = "nacos.config.server-addr";

    private static final String NACOS_DATA_ID = "nacos.config.dataid";

    private static final String NACOS_GROUP = "nacos.config.group";

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        String serverAddr = environment.getProperty(NACOS_CONFIG_SERVER_ADDR);
        String dataId = environment.getProperty(NACOS_DATA_ID);
        String group = environment.getProperty(NACOS_GROUP);
        Properties properties = new Properties();
        try {
            ConfigService configService = NacosFactory.createConfigService(serverAddr);
            String config = configService.getConfig(dataId, group, 5000);
            String[] split = config.split("\n");
            for (String s : split) {
                if (s.startsWith("#") || StringUtils.isBlank(s)) {
                    continue;
                }
                String[] split1 = s.split("=");
                properties.put(split1[0], split1[1]);
            }
            PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource("nacosConfig", properties);
            environment.getPropertySources().addLast(propertiesPropertySource);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }
}
