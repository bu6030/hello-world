package com.example.demo.springboottest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Slf4j
@Service
public class SpringBootTestService {
    @Autowired
    private SpringBootTestRepository springBootTestRepository;
    @Autowired
    private RestTemplate restTemplate;
    //注意key，可以配置在cache中的名字，同时可以将参数作为key的一部分存在key中
    @Cacheable(key = "'student_'+#id")
    public StudentEntity getStudent(String id) {
        log.info("只有第一次没有缓存时候才调用，第二次就没有日志了，因为请求就不进入方法里了");
        Optional<StudentEntity> opt = springBootTestRepository.findById(id);
        //存在返回的Student对象
        if(opt.isPresent()){
            return opt.get();
        }
        //不存在返回null
        return null;
    }

    public String getStudentByRestTemplate(String id){
        // 拼接好url，以及参数，拼接完成的URL为http://127.0.0.1:8082/hello-world-new/student?id=1
        String url = UriComponentsBuilder.fromUriString("http://127.0.0.1:8082/hello-world-new/springboottest/student")
                .queryParam("id", id)
                .build()
                .toUriString();
        // 由于获取student接口咱们设置了basicauth，所以需要在请求时配置
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("aaa", "bbb");
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = null;
        try {
            // 通过Get方式调用接口
            response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return response.getBody();
    }

    public String addStudentByRestTemplate(String id, String name) {
        String url = "http://127.0.0.1:8082/hello-world-new/springboottest/student";
        // 由于获取student接口咱们设置了basicauth，所以需要在请求时配置
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("aaa", "bbb");
        // 需要传递FORMDATA的请求参数
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        // POST参数通过LinkedMultiValueMap传递过去，通过HashMap会报下面这个错
        // No HttpMessageConverter for java.util.HashMap and content type "multipart/form-data"
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("id", id);
        params.add("name", name);
        HttpEntity httpEntity = new HttpEntity(params, headers);
        ResponseEntity<String> response = null;
        try {
            // 通过POST方式调用接口，如果需要PUT，DELETE方式调用
            // 只需要修改为HttpMethod.PUT，HttpMethod.DELETE即可，其他传递参数已经代码一致
            response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return response.getBody();
    }
}
