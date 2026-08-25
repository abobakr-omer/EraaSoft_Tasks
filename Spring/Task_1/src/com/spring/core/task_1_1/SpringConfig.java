package com.spring.core.task_1_1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.spring.core.task_1_1")
@PropertySource("classpath:data.properties")
public class SpringConfig {
}
