package com.example;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

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
        Thread.sleep(50);
        ResponseEntity<String> resp=restTemplate.getForEntity(url,String.class);
        log.info("going back from service1");
        return ResponseEntity.ok("From service1 "+resp.getBody());
    }
}
