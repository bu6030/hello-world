package com.example.demo;

import com.example.demo.springboottest.SpringBootTestRepository;
import com.example.demo.springboottest.SpringBootTestService;
import com.example.demo.springboottest.StudentEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Service中含有RestTemplate和Repository的注入
 * 因此需要通过AutoConfigureDataJpa引入Repository
 * 需要通过@AutoConfigureWebClient(registerRestTemplate=true)引入restTemplate
 */
@RunWith(SpringRunner.class)
@RestClientTest(SpringBootTestService.class)
@AutoConfigureDataJpa
@AutoConfigureWebClient(registerRestTemplate=true)
public class ServiceTest {

    // 调用URL的MockBean
    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private SpringBootTestService springBootTestService;

    @Autowired
    private SpringBootTestRepository springBootTestRepository;

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * URL要与调用的路径完全一致，包括后边的?id=1
     * andRespond设定返回的body格式
     * MediaType.APPLICATION_JSON表示结果json格式
     * 最后通过Assert判断结果
     * @throws Exception
     */
    @Test
    public void getStudentByRestTemplate() throws Exception {
        StudentEntity student = new StudentEntity("1","张三");
        this.server.expect(requestTo("http://127.0.0.1:8082/hello-world-new/springboottest/student?id=1"))
                .andRespond(withSuccess(mapper.writeValueAsString(student), MediaType.APPLICATION_JSON));
        String response = springBootTestService.getStudentByRestTemplate("1");
        Assert.assertEquals("张三", mapper.readValue(response, StudentEntity.class).getName());
    }

    /**
     * MediaType.TEXT_PLAIN表示结果是纯文本格式
     * 最后通过Assert判断结果
     * @throws Exception
     */
    @Test
    public void addStudentByRestTemplate() throws Exception {
        this.server.expect(requestTo("http://127.0.0.1:8082/hello-world-new/springboottest/student"))
                .andRespond(withSuccess("ok", MediaType.TEXT_PLAIN));
        Assert.assertEquals("ok", springBootTestService.addStudentByRestTemplate("1", "张三"));

    }
}
