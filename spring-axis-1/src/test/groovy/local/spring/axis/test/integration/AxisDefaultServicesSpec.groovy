package local.spring.axis.test.integration

import local.spring.axis.test.utils.SpringAxisIntegrationSpecification
import org.apache.axis.client.AdminClient
import org.apache.axis.client.Call
import org.apache.http.client.fluent.Request
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType

import static org.hamcrest.Matchers.equalToIgnoringCase
import static spock.util.matcher.HamcrestSupport.expect

/**
 * Created by plks on 2016-02-25.
 */
class AxisDefaultServicesSpec extends SpringAxisIntegrationSpecification {

    def "getVersion remote call should return apache axis current version"() {
        setup:
        Call call = createCallToAxis("/axis");

        when:
        String result = (String) call.invoke('Version', 'getVersion', null);

        then:
        notThrown(Exception);
        result equalToIgnoringCase(org.apache.axis.Version.getVersion());
    }

    def "download wsdl for Version service"() {
        when:
        def response = Request.Get(uriBuilder().setPath("/axis/Version").addParameter("wsdl", "").toString())
                .execute().returnResponse();

        then:
        response.statusLine.getStatusCode() == 200;
        response.getFirstHeader("Content-Type").getValue().startsWith(MediaType.TEXT_XML_VALUE);
    }

    def "there should be possibility to deploy wsdd file"() {
        setup:
        ClassPathResource deploymentDescription = new ClassPathResource("hello_world_spring_rpc.wsdd");
        AdminClient client = new AdminClient(true);
        client.setTargetEndpointAddress(uriBuilder().setPath("/axis").build().toURL());

        when:
        def result = client.process(deploymentDescription.inputStream);

        then:
        notThrown(Exception);
        expect result, equalToIgnoringCase("<Admin>Done processing</Admin>");
    }

    def "undeploying non existing service"() {
        setup:
        AdminClient client = new AdminClient(true);
        client.setTargetEndpointAddress(uriBuilder().setPath("/axis").build().toURL());

        when:
        def result = client.undeployService("someNonExistingService!@#");

        then:
        notThrown(Exception);
        expect result, equalToIgnoringCase("<Admin>Done processing</Admin>");
    }
}
