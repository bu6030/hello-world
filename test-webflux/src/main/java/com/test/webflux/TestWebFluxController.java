package com.test.webflux;

import com.test.webflux.mapper.Student;
import com.test.webflux.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;

@Slf4j
@RestController
public class TestWebFluxController {

    @Autowired
    private StudentMapper studentMapper;
    /**
     * Meno返回同步的返回结果
     * @return
     */
    @RequestMapping(value = "/studentMenoSync", method = RequestMethod.GET)
    public Mono<Object> getStudent(@RequestParam Long id){
        return Mono.justOrEmpty(studentMapper.getStudent(id));
    }
    /**
     * Meno返回异步的返回结果，但是对于客户端来说仍然是同步
     * 是不是只有通过stream的方式获取的才算是异步的
     * @return
     */
    @RequestMapping(value = "/studentMenoASync", method = RequestMethod.POST)
    public Mono<Object> saveStudent(@RequestBody Student student) {
        log.info("Student: {}", student);
        // 异步保存用户到数据库
        return Mono.fromCallable(() -> studentMapper.saveStudent(student));
    }

    /**
     * Flux返回同步的结果
     * @return
     */
    @ResponseBody
    @GetMapping(value="/studentsFluxSync")
    public Flux<Object> getStudents() {
        List<Student> students = studentMapper.getStudents();
        return Flux.fromIterable(students);
    }

    /**
     * Flux返回异步的结果，分成几次将所有的student返回
     * 通过了Stream的方式才是异步的
     * @return
     */
    @ResponseBody
    @GetMapping(value="/studentsFluxASync", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> studentsFluxASync() {
        return Flux.fromStream(studentMapper.getStudents().stream())
                .subscribeOn(Schedulers.parallel());
    }

    /**
     * Flux返回异步的结果，每5秒返回id最大的student
     * 通过了Stream的方式才是异步的
     * @return
     */
    @ResponseBody
    @GetMapping(value="/studentsFluxASync2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> studentsFluxASync2() {
        return Flux.interval(Duration.ofSeconds(5)).map(s -> studentMapper.getMaxIdStudent().get(0));
    }
}
