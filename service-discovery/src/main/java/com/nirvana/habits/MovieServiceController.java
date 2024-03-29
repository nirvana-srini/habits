package com.nirvana.habits;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieServiceController {

    @GetMapping("api/movies")
    public String getMessage() throws InterruptedException {
        Thread.sleep(5000);
        return "Hello World!";
    }
}
