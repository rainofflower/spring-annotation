package com.yanghui.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ${DESCRIPTION}
 *
 * @author yanghui
 * @date 2020-09-16
 **/
@ConfigurationProperties(prefix = "a")
@Data
public class AProperties {

    private String type = "TYPE-A";

    private String host;

    private String port;

    private String password;
}
