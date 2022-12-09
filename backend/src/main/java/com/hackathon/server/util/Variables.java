package com.hackathon.server.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Variables {

    @Value("${spring.profiles.active}")
    public String springEnvValue;
//
    @Value("${document.files.path}")
    private String filesPathValue;

    public static String filesPath;
    public static String springEnv;

    public Variables() { }

    @PostConstruct
    private void setValues() {
        filesPath=filesPathValue;
        springEnv = springEnvValue;
    }



}
