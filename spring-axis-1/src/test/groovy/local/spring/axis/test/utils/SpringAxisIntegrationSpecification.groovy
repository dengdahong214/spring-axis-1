package local.spring.axis.test.utils

import local.spring.axis.WebApplication
import org.apache.axis.client.Call
import org.apache.axis.client.Service
import org.apache.http.client.utils.URIBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import spock.lang.Specification

/**
 * Created by plks on 2016-02-25.
 */
@SpringApplicationConfiguration(classes=[
        WebApplication.class
])
@WebIntegrationTest("server.port=0")
abstract class SpringAxisIntegrationSpecification extends Specification {
    @Value('${local.server.port}')
    int port;

    def uriBuilder() {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost("localhost").setPort(port);
        return uriBuilder;
    }

    def createCallToAxis(endpoint) {
        Service service = new Service();
        Call call = service.createCall();
        call.setTargetEndpointAddress(uriBuilder().setPath(endpoint).build().toURL());
        return call;
    }
}
