package com.corbat.kubernetes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {

    @GetMapping
    public Map<String, String> sayHello() {
        final OffsetDateTime now = OffsetDateTime.now();
        log.info("Received request to /hello endpoint at {}", now);
        return Map.of("message", "Hello from Spring Boot with Kubernetes application!");
    }
}
