package local.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    public String helloWorld() {
        return "Hello World.";
    }

    public String greet (String name) { return "Hello " + name; }
}
