package com.yanghui.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ${DESCRIPTION}
 *
 * @author yanghui
 * @date 2020-09-16 11:14
 **/
@Configuration
@EnableConfigurationProperties({AProperties.class, BProperties.class})
public class FooAutoConfig {
}
