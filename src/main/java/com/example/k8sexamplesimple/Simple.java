package com.example.k8sexamplesimple;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class Simple {

    @GetMapping("/")
    public String simple() {
        return "Hello simple";
    }
}
