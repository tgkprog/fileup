package com.bb.fileUp.conf;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.ApplicationContext;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.*;
@Configuration
public class SpringConfig implements ApplicationListener<ApplicationReadyEvent>
{

    @Value("${a.b:0}")
    String ab;

    @Value("${spring.servlet.multipart.enabled:k}")
    String ab1;


    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("Sprng File up parse :" + ab1);
        System.out.println("ab " + ab);
        System.out.println("a");
    }
}
