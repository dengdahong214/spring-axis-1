package local.service;

import org.springframework.stereotype.Service;

/**
 * Created by plks on 2016-03-02.
 */
@Service
public class ServiceDeployedOnStartup {

    public String someMethod() {
        return "someMethod";
    }

}
