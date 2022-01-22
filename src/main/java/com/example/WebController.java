package com.example;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Controller("/")
@Slf4j
public class WebController {

    @Autowired
    RestTemplate restTemplate;

    private final String BASE_URL="http://localhost:8082";
    private final String REQ_PATH="service2";
    private final String PARAM="msg";

    @GetMapping("/service1")
    @SneakyThrows
    public ResponseEntity<String> getMsg(){
        String url=BASE_URL+"/"+REQ_PATH;
        log.info("came to service1");
        Thread.sleep(1500);
        ResponseEntity<String> resp=restTemplate.getForEntity(url,String.class);
        log.info("going back from service1");
        return ResponseEntity.ok("From service1 "+resp.getBody());
    }
}
