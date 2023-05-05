package com.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public Object getStudent(@RequestParam String id){
        log.info("Request id {}", id);
        return demoService.getDemoEntity(id);
    }

    @RequestMapping(value = "/demo", method = RequestMethod.POST)
    public String addStudent(@RequestBody DemoEntity demo){
        demoService.updateDemoEntity(demo.getId(),demo.getName(), demo.getName());
        return "ok";
    }

    @RequestMapping(value = "/demo", method = RequestMethod.PUT)
    public String updateStudent(@RequestBody DemoEntity demo){
        demoService.updateDemoEntity(demo.getId(),demo.getName(), demo.getName());
        return "ok";
    }

    @RequestMapping(value = "/demo", method = RequestMethod.DELETE)
    public String deleteStudent(@RequestParam String id){
        demoService.removeDemoEntity(id);
        return "ok";
    }
}
