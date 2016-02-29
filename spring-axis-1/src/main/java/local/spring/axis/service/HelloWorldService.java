package local.spring.axis.service;

import org.springframework.stereotype.Component;

/**
 *
 * @author plks
 */
@Component
public class HelloWorldService {
    public String helloWorld() {
        return "Hello World.";
    }
}
