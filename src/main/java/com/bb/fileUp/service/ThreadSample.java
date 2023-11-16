package com.bb.fileUp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ThreadSample implements ApplicationListener<ApplicationReadyEvent>,  Runnable
{
    private volatile double i;
    public void run(){
        while(true) {
            i++;
            log.info("Running " + i);
            try{
                Thread.sleep(1500);
            }catch (Exception e){
                log.error("Sleep ",e);
            }
        }

    }

    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("Sprng File up parse " );
        Thread th = new Thread(this);
        th.start();
        System.out.println("a");
    }
}
