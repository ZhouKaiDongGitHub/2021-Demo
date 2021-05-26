package org.kzhou.user.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class FeignConfig {

    @Bean
    public Logger.Level level(){
        // Feign自己的日志级别 ：NONE BASIC HEADERS FULL
        return Logger.Level.BASIC;
    }
}
