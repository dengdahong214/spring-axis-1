/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.config;

import org.apache.axis.EngineConfigurationFactory;
import org.apache.axis.transport.http.AdminServlet;
import org.apache.axis.transport.http.AxisServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author plks
 */
@Configuration
public class AxisConfig {
    
    @Bean
    public ServletRegistrationBean axisServletRegistrationBean() {
        System.setProperty(EngineConfigurationFactory.SYSTEM_PROPERTY_NAME, local.axis.EngineConfigurationFactory.class.getName());
        AxisServlet servlet = new AxisServlet();
        ServletRegistrationBean servletBean = new ServletRegistrationBean(servlet, "/axis/*", "/axis-secured/*");
        return servletBean;
    }
    
    @Bean
    public ServletRegistrationBean axisAdminServletRegistrationBean() {
        ServletRegistrationBean servletBean = new ServletRegistrationBean(new AdminServlet(), "/axis-admin");
        servletBean.setLoadOnStartup(100);
        return servletBean;
    }
}
