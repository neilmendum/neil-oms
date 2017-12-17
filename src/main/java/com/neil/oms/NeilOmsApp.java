package com.neil.oms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by neilmendum on 16/12/2017.
 */
@SpringBootApplication
public class NeilOmsApp {

    private static final Logger log = LoggerFactory.getLogger(NeilOmsApp.class);

    public static void main(String[] args) {
        SpringApplication.run(NeilOmsApp.class, args);
    }
}
