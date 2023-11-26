package com.bb.fileUp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class FileUploadMainApp {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ///SpringApplication.run(FileUploadMainApp.class, args);

        SpringApplication application = new SpringApplication(FileUploadMainApp.class);
        //application.setBannerMode(Banner.Mode.OFF);
        Set<String> sources = application.getSources();
//        int i =0;
//        for(String s: sources){
//            System.out.println((++i) + "Source :" +  s);
//        }
        application.run(args);

    }

}