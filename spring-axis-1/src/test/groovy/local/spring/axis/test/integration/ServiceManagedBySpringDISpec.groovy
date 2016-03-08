package local.spring.axis.test.integration

import local.spring.axis.test.utils.SpringAxisIntegrationSpecification

import static org.hamcrest.Matchers.equalToIgnoringCase

/**
 * Created by plks on 2016-03-04.
 */
class ServiceManagedBySpringDISpec extends SpringAxisIntegrationSpecification {

    def "calling helloWorld method of service-managed-by-spring-di service should return hello world"() {
        given:
        def call = createCallToAxis("/axis");

        when:
        def response = (String) call.invoke("service-managed-by-spring-di", "helloWorld", null);

        then:
        response equalToIgnoringCase("hello world. From service managed by spring di.");
    }

}
