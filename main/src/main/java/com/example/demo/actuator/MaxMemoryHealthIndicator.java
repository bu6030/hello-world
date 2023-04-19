package com.example.demo.actuator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MaxMemoryHealthIndicator implements HealthIndicator {
    /**
     * 自己手动实现actuator/health服务状态
     * @return
     */
    @Override
    public Health health() {
        // 设置内存小于10G的，手动设置actuator/health的状态为DOWN
        // 只有大于10G，状态才是UP
        Long minMemory = 10L*1024L*1024L*1024L;
        log.info("Runtime max memory: {}MB , min memory: {}MB",Runtime.getRuntime().maxMemory()/1024/1024 , minMemory/1024/1024);
        boolean invalid = Runtime.getRuntime().maxMemory() < minMemory;
        Status status = invalid ? Status.DOWN : Status.UP;
        return Health.status(status).build();
    }
}
