package ru.hogwarts.example.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/info")

public class InfoController {


    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public String getPort() {
        return port;
    }

    private final Logger logger = LoggerFactory.getLogger(InfoController.class);

    @GetMapping ("returnIntegerValue")
    public void getIntegerValue() {
        long start = System.currentTimeMillis();
        int sum = Stream.iterate(1, a -> a +1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );

        long finish = System.currentTimeMillis();

        logger.info("Result: {}, execution time: {}", sum, finish - start);;
    }

    @GetMapping ("returnIntegerValue-parallel")
    public void getIntegerValueParallel() {
        long start = System.currentTimeMillis();
        int sum = Stream.iterate(1, a -> a +1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );

        long finish = System.currentTimeMillis();

        logger.info("Result parallel: {}, execution time: {}", sum, finish - start);;
    }
}
