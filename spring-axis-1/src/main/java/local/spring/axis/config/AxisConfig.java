/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.spring.axis.config;

import javax.servlet.Servlet;
import javax.xml.namespace.QName;
import local.spring.axis.wsdd.provider.WSDDSpringRPCProvider;
import org.apache.axis.deployment.wsdd.WSDDConstants;
import org.apache.axis.deployment.wsdd.WSDDProvider;
import org.apache.axis.transport.http.AdminServlet;
import org.apache.axis.transport.http.AxisServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;

/**
 *
 * @author plks
 */
@Configuration
public class AxisConfig {
    
    @Bean
    public ServletRegistrationBean axisServletRegistrationBean() {
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
