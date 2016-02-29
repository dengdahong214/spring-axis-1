package local.spring.axis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 *
 * @author plks
 */
@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(APPLICATION_CLASS, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(APPLICATION_CLASS);
    }
	
	private final static Class<WebApplication> APPLICATION_CLASS = WebApplication.class;
}
