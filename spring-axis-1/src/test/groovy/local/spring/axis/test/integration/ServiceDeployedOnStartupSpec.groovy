package local.spring.axis.test.integration

import local.spring.axis.test.utils.SpringAxisIntegrationSpecification

import static org.hamcrest.Matchers.equalToIgnoringCase

/**
 * Created by plks on 2016-03-02.
 */
class ServiceDeployedOnStartupSpec extends SpringAxisIntegrationSpecification {

    def "calling someMethod on service deployed on startup should return correct response"() {
        given:
        def call = createCallToAxis("/axis");

        when:
        def response = (String) call.invoke("service-deployed-on-startup", "someMethod", null);

        then:
        response equalToIgnoringCase("someMethod");
    }

}
