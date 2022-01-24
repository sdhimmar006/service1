package com.example;

import com.google.cloud.opentelemetry.trace.TraceConfiguration;
import com.google.cloud.opentelemetry.trace.TraceExporter;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

//    @Value("projid")
//    String projId;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @SneakyThrows
    public SpanExporter googleTraceExporter() {
        return TraceExporter.createWithConfiguration(
                TraceConfiguration.builder()
//                        .setProjectId(projId)
                        .build()
        );
    }


}
