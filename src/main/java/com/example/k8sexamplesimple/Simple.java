package com.example.k8sexamplesimple;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class Simple {

    @Value("${app.custom.message}")
    private String customMessage;

    @Value("${app.custom.secret}")
    private String secretValue;

    @GetMapping("/")
    public String simple() {
        return customMessage;
    }

    @GetMapping("/secret")
    public String simpleSecret() {
        return secretValue;
    }
}
