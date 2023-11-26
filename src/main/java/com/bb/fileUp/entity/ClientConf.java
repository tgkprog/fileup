package com.bb.fileUp.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "bb-clients")
@Data
@Slf4j
public class ClientConf  implements ApplicationListener<ApplicationReadyEvent> {
    private String name;
    private String id;
    private Map<String, Client> clients = new HashMap<>();
    @Data
    public static class Client{
        private String handler;
        private int max;
        private int max2;

        public void doThat(){
            log.info("a");
        }
    }

    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("ClientConf " + this);
        log.info("ClientConf sz" + this.clients.size());
        Collection<Client> vals = this.clients.values();
        Client cl0 = vals.iterator().next();

    }
}
/*
bbclients:
  clients:
    c1:
      handler: L
      max: 300
      max2: 30
    c2:
      handler: B
      max: 304
      max2: 35*/