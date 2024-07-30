package org.keycloak;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class KyecloakController {

    @RequestMapping(value = "/helloWorld", method = RequestMethod.GET)
    public Object helloWorld() {
        return "Hello World!";
    }

    @RequestMapping(value = "/helloWorld1", method = RequestMethod.GET)
    public Object helloWorld1() {
        return "Hello World1!";
    }
}
