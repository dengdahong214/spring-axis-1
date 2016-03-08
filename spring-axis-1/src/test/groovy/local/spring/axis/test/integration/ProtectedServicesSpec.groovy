package local.spring.axis.test.integration

import local.spring.axis.test.utils.SpringAxisIntegrationSpecification
import org.apache.axis.AxisFault
import org.apache.axis.client.Call

import static org.hamcrest.Matchers.equalToIgnoringCase
import static org.hamcrest.Matchers.startsWith
import static spock.util.matcher.HamcrestSupport.expect

/**
 * Created by plks on 2016-02-29.
 */
class ProtectedServicesSpec extends SpringAxisIntegrationSpecification {

    def "accessing axis service protected with http basic auth should give 401"() {
        given:
            Call call = createCallToAxis("/axis-secured");

        when:
            def result = (String) call.invoke('Version', 'getVersion', null);

        then:
            AxisFault e = thrown(AxisFault);
            expect e.faultReason, startsWith("(401)");

    }

    def "accessing axis service protected with http basic auth using incorrect credentials should give 401"() {
        given:
            Call call = createCallToAxis("/axis-secured");
            call.setUsername("admin");
            call.setPassword("some_password_fdkdfjd232fs!@#FSD%^&&");

        when:
            def result = call.invoke("Version", 'getVersion', null);

        then:
            AxisFault e = thrown(AxisFault);
            expect e.faultReason, startsWith("(401)");
    }

    def "accessing axis service protected with http basic auth using login/passwod should return correct value"() {
        given:
            Call call = createCallToAxis("/axis-secured");
            call.setUsername("admin");
            call.setPassword("admin");

        when:
            def result = (String) call.invoke('Version', 'getVersion', null);

        then:
            result equalToIgnoringCase(org.apache.axis.Version.getVersion());
    }

}
