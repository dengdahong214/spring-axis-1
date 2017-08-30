package local.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceDeployedOnStartup {

    public String someMethod() {
        return "someMethod";
    }

}
