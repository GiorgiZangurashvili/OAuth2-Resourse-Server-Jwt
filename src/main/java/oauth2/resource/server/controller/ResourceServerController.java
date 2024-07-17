package oauth2.resource.server.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceServerController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        return "Hello World";
    }

}
