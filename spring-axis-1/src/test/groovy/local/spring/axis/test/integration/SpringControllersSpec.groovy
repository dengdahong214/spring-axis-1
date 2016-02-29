package local.spring.axis.test.integration

import local.spring.axis.test.utils.SpringAxisIntegrationSpecification

import static spock.util.matcher.HamcrestSupport.expect

import org.apache.http.client.fluent.Request
import org.junit.Test

import static org.hamcrest.Matchers.equalToIgnoringCase

/**
 * Created by plks on 2016-02-25.
 */
class SpringControllersSpec extends SpringAxisIntegrationSpecification {

    @Test
    def "request to hello-world should return 'hello world from spring controller'"() {
        when:
        def response = Request.Get(uriBuilder().setPath("/hello-world").toString())
            .execute().returnContent();

        then:
        expect response.asString(), equalToIgnoringCase("hello world from spring controller");
    }

}
