package local.spring.axis.test.integration

import local.spring.axis.test.utils.SpringAxisIntegrationSpecification
import org.apache.axis.client.AdminClient
import org.apache.http.client.fluent.Request
import org.apache.http.client.utils.URIBuilder
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType

import static org.hamcrest.Matchers.equalToIgnoringCase

/**
 * Created by plks on 2016-02-25.
 */
class HelloWorldManuallyDeployedServiceSpec
        extends SpringAxisIntegrationSpecification {

    def setup() {
        ClassPathResource helloWorldServiceDescription = new ClassPathResource("hello_world_spring_rpc.wsdd");
        AdminClient adminClient = new AdminClient();
        adminClient.setTargetEndpointAddress(uriBuilder().setPath("/axis").build().toURL());
        adminClient.process(helloWorldServiceDescription.inputStream);
    }

    def cleanup() {
        AdminClient adminClient = new AdminClient();
        adminClient.setTargetEndpointAddress(uriBuilder().setPath("/axis").build().toURL());
        adminClient.undeployService("hello-world");
    }

    def "there should be possibility to get wsdl for service"() {
        when:
        def response = Request.Get(uriBuilder().setPath("/axis/hello-world").addParameter("wsdl", "").toString())
            .execute().returnResponse();

        then:
        response.statusLine.statusCode == 200;
        response.getFirstHeader("Content-Type").value.startsWith(MediaType.TEXT_XML_VALUE);
    }

    def "calling helloWorld method of hello-world service should return hello world"() {
        given:
        def call = createCallToAxis();

        when:
        def response = (String) call.invoke("hello-world", "helloWorld", null);

        then:
        response equalToIgnoringCase("hello world.");
    }

    def "calling greet method of hello-world service with parameter ala should return hello ala"() {
        given:
        def sampleName = "ala"
        def call = createCallToAxis();

        when:
        def response = (String) call.invoke("hello-world", "greet", sampleName);

        then:
        response equalToIgnoringCase("hello " + sampleName);
    }

    def uriBuilder() {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost("localhost").setPort(port);
        return uriBuilder;
    }

    def createCallToAxis() {
        return createCallToAxis("/axis");
    }

}
