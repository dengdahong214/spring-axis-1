# Spring Axis 1

Hello world example of Axis 1 ( Axis1 ) setup on Spring Boot 1.3.

# Usage
```
cd spring-axis-1
mvn clean package
java -jar target/spring-axis-1.jar
```

or simply run
```
WebApplication.java
```

# Spring integration

New Apache Axis provider was created in order to provide Spring DI integration. Implementation of this provider is defined by [SpringRPCProvider](spring-axis-1/src/main/java/local/axis/SpringRPCProvider.java) class.

Before the new provider can be used it needs to be registered in Axis. This is done by [WSDDSpringRPCProvider](spring-axis-1/src/main/java/local/axis/WSDDSpringRPCProvider.java) class when Spring application context emits ContextRefreshedEvent.

In order to configure service so that it utilize `SpringRPCProvider` you need following xml in your wsdd file:
```
<service name="service-managed-by-spring-di" provider="java:SpringRPC">
  <parameter name="className" value="local.spring.axis.service.ServiceManagedBySpringDI"/>
  <parameter name="allowedMethods" value="*"/>
</service>
```
With this configuration, whenever Apache Axis has to invoke method on `service-managed-by-spring-di` service, it will use  `SpringRPCProvider` to obtain instance of the service from dependency container. The instance will be searched by the given class name.

If you want to lookup service instance by qualifier you may use following configuration:
```
<service name="service-managed-by-spring-di" provider="java:SpringRPC">
  <parameter name="qualifier" value="someServiceQualifier"/>
  <parameter name="allowedMethods" value="*"/>
</service>
```
## Executable jar

In case of executable jar packages with embedded tomcat there was an issue: Axis couldn't load server-wsdd.xml config file from jar. In order to fix this new implementation of [EngineConfigurationFactory](https://github.com/pawelkorus/spring-axis-1/blob/master/spring-axis-1/src/main/java/local/axis/EngineConfigurationFactory.java) was introduced which additionally uses class loader to load resources from class path.

# Interacting with server

To see list of available services you may open following url: [http://localhost:8080/axis](http://localhost:8080/axis)

If you want to download wsdl file for specific service you may use following url template `http://localhost:8080/axis/<SERVICE_NAME>?wsdl`, i.e. to get wsdl for Version service you should use [http://localhost:8080/axis/Version?wsdl](http://localhost:8080/axis/Version?wsdl).

Axis 1 comes with AdminClient which you may use to deploy wsdd files. For details see [Axis User's Guide](http://axis.apache.org/axis/java/user-guide.html#Using_the_AdminClient).
