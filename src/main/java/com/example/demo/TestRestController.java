package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
public class TestRestController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StudentService studentService;
    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private StudentRepository studentRepository;
//    @GetMapping("/student")
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Object getStudent(@RequestParam String id){
        Optional<Student> opt = studentRepository.findById(id);
        //存在返回的Student对象
        if(opt.isPresent()){
            return opt.get();
        }
        //不存在返回null
        return null;
    }

    @RequestMapping(value = "/student/cache", method = RequestMethod.GET)
    public Object getStudentFromCache(@RequestParam String id){
        return studentService.getStudent(id);
    }

//    @PostMapping("/student")
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestParam String id, @RequestParam String name){
        studentRepository.save(new Student(id,name));
        return "ok";
    }

//    @PutMapping("/student")
    @RequestMapping(value = "/student", method = RequestMethod.PUT)
    public String updateStudent(@RequestParam String id, @RequestParam String name){
        studentRepository.save(new Student(id,name));
        return "ok";
    }

//    @DeleteMapping("/student")
    @RequestMapping(value = "/student", method = RequestMethod.DELETE)
    public String deleteStudent(@RequestParam String id){
        studentRepository.deleteById(id);
        return "ok";
    }

    @PostMapping("/validation")
    public String validation(@Validated @RequestBody ValidationRequest request, BindingResult results) {
        //把实体注解中的错误信息返回
        if (results.hasErrors()) {
            return results.getFieldError().getDefaultMessage();
        }
        return "ok";
    }

    @GetMapping("/getStudentByRestTemplate")
    public String getStudentByRestTemplate(@RequestParam String id) {
        // 拼接好url，以及参数，拼接完成的URL为http://127.0.0.1:8082/hello-world-new/student?id=1
        String url = UriComponentsBuilder.fromUriString("http://127.0.0.1:8082/hello-world-new/student")
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

    @PostMapping("/addStudentByRestTemplate")
    public String addStudentByRestTemplate(@RequestParam String id, @RequestParam String name) {
        String url = "http://127.0.0.1:8082/hello-world-new/student";
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

    @PostMapping("/sendMQMessage")
    public String sendMQMessage(@RequestParam String content) {
        messageProducer.sendMessageToMQ(content);
        return "ok";
    }
}
