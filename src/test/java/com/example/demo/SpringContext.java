package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringContext {

    private static volatile ConfigurableApplicationContext context;

    public static void setContext() {
        context = SpringApplication.run(DemoApplication.class);
    }

    public static void autowireBean(Object obj) {
        context.getAutowireCapableBeanFactory().autowireBean(obj);
    }

    public static void close() {
        context.close();
    }


}
