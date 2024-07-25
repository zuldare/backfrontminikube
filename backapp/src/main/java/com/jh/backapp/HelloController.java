package com.jh.backapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;

@RestController
public class HelloController {

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String hello() throws UnknownHostException {
        logger.info("Received request at {}  ", Instant.now()  );
        return "This is from backend " + Instant.now().toString() + " from: " + InetAddress.getLocalHost().getHostName();
    }

}
