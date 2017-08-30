package local.spring.axis.test.utils

import local.WebApplication
import org.apache.axis.client.Call
import org.apache.axis.client.Service
import org.apache.http.client.utils.URIBuilder
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = WebApplication)
abstract class SpringAxisIntegrationSpecification extends Specification {
    @LocalServerPort
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
