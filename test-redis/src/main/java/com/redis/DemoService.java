package com.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
@CacheConfig(cacheNames = "demo")
public class DemoService {
    private static HashMap<String, DemoEntity> demoMap = new HashMap();
    @Cacheable(key = "'demo_'+#id")
    public DemoEntity getDemoEntity(String id) {
        log.info("只有第一次没有缓存时候才调用，第二次就没有日志了，因为请求就不进入方法里了");
        if(demoMap.containsKey(id)){
            return demoMap.get(id);
        }
        return null;
    }
    @CachePut(key = "'demo_'+#id")
    public DemoEntity updateDemoEntity(String id, String name, String gender) {
        log.info("更新每次都进入");
        demoMap.put(id, DemoEntity.builder().id(id).name(name).gender(gender).build());
        return demoMap.get(id);
    }

    @CacheEvict(key = "'demo_'+#id")
    public void removeDemoEntity(String id) {
        log.info("删除每次都进入");
        demoMap.remove(id);
    }

}
