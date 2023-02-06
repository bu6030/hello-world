package com.example.demo;

import com.example.demo.springboottest.SpringBootTestRepository;
import com.example.demo.springboottest.SpringBootTestService;
import com.example.demo.springboottest.StudentEntity;
import com.example.demo.springboottest.ValidationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    RestTemplate restTemplate;

    @MockBean
    SpringBootTestService springBootTestService;

    @MockBean
    SpringBootTestRepository springBootTestRepository;

    HttpHeaders httpHeaders = new HttpHeaders();
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 由于咱们系统设定了权限控制
     * 在Test请求之前设定basicAuth的信息
     * @throws Exception
     */
    @Before
    public void setBasicAuth() throws Exception {
        // 设置basicAuth
        String basicAuthString = "Basic " + Base64.getEncoder().encodeToString("aaa:bbb".getBytes());
        httpHeaders.set("Authorization", basicAuthString);
    }

    @Test
    public void TestRestController_getStudent() throws Exception {
        StudentEntity expectStudent = new StudentEntity("1","张三");
        // 通过when的方式mockrepository的返回为expectStudent
        when(springBootTestRepository.findById(any())).thenReturn(Optional.of(expectStudent));
        // 通过mockMvc模拟调用/springboottest/student?id=1
        // 也就是SpringBootTestController的getStudent方法
        // mockMvc.perfrom中传入的get表示GET请求
        MvcResult mvcResult = mockMvc.perform(get("/springboottest/student?id=1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                // 设定basicAuth到请求header中
                        .headers(httpHeaders))
                // 打印详细的请求以及返回内容
                .andDo(print())
                // 判断HttpStatus是200，如果不是表示失败
                .andExpect(status().isOk())
                // 返回结果给mvcResult
                .andReturn();
        // 获取mvcResult的body
        String resutlStr = mvcResult.getResponse().getContentAsString(Charset.defaultCharset());
        // 将json格式的resutlStr反序列化为StudentEntity
        StudentEntity student = mapper.readValue(resutlStr, StudentEntity.class);
        // 判断结果是否成功
        Assert.assertEquals(expectStudent.getName(), student.getName());

    }

    /**
     * 接口代码中通过@RequestParam接收的
     * 因此此处ContentType为MediaType.MULTIPART_FORM_DATA
     * 通过param设定请求的参数key/value
     * 分别
     * @throws Exception
     */
    @Test
    public void TestRestController_addStudent() throws Exception {
        // mockMvc.perfrom中传入的post表示POST请求
        MvcResult mvcResult = mockMvc.perform(post("/springboottest/student")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .headers(httpHeaders).param("id", "1").param("name","张三"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("ok", mvcResult.getResponse()
                .getContentAsString(Charset.defaultCharset()));
    }

    @Test
    public void TestRestController_updateStudent() throws Exception {
        // mockMvc.perfrom中传入的put表示PUT请求
        MvcResult mvcResult = mockMvc.perform(put("/springboottest/student")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .headers(httpHeaders).param("id", "1").param("name","张三1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("ok", mvcResult.getResponse()
                .getContentAsString(Charset.defaultCharset()));
    }

    @Test
    public void TestRestController_deleteStudent() throws Exception {
        // mockMvc.perfrom中传入的delete表示DELETE请求
        MvcResult mvcResult = mockMvc.perform(delete("/springboottest/student")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .headers(httpHeaders).param("id", "1").param("name","张三1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("ok", mvcResult.getResponse()
                .getContentAsString(Charset.defaultCharset()));
    }

    @Test
    public void TestRestController_validation() throws Exception {
        // validation接口中通过@RequestBody获取的请求参数
        // 因此此时contentType为MediaType.APPLICATION_JSON_VALUE
        // 请求参数也需要通过mapper把bean序列化成json格式
        MvcResult mvcResult = mockMvc.perform(post("/springboottest/validation")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .headers(httpHeaders)
                        .content(mapper.writeValueAsString(ValidationRequest.builder().age("35")
                                .content("12345678901").count(10).name("张三").remark("备注").build())))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("ok", mvcResult.getResponse()
                .getContentAsString(Charset.defaultCharset()));
    }
}
