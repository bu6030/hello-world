package com.example.demo.actuator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Endpoint来创建/actuator/readiness
 */
@Slf4j
@Component
@Endpoint(id="readiness")
public class ReadinessEndpoint {

    /**
     * Get方式调用接口/actuator/readiness
     */
    private String ready = "NOT_READY";
    @ReadOperation
    public String getReadiness(){
        return ready;
    }

    /**
     * Post方式调用接口/actuator/readiness
     */
    @WriteOperation
    public String writeOperation(){
        return ready+"_WRITE";
    }

    /**
     * Delete方式调用接口/actuator/readiness
     */
    @DeleteOperation
    public String deleteOperation(){
        return ready+"_DELETE";
    }

    /**
     * 监听服务启动完毕
     */
    @EventListener(ApplicationReadyEvent.class)
    public void setReady(){
        log.info("Application start complete setReady");
        ready = "READY";
    }
}