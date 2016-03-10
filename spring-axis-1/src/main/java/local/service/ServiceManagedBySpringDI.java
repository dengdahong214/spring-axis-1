package local.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service is an example of service managed by spring dendency container.
 *
 * When configuring deployment file for such services you have to use java:SpringRPC
 * provider. For reference you may check configuration of service-managed-by-spring-di
 * service in service-config.wsdd file.
 *
 * Created by plks on 2016-03-04.
 */
@Service
public class ServiceManagedBySpringDI {

    @Autowired
    HelloWorldService helloWorldService;

    public String helloWorld() {
        return helloWorldService.helloWorld() + " From service managed by spring di.";
    }
}
