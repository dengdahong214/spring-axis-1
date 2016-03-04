# Spring Axis 1

Hello world example of Axis 1 ( Axis1 ) setup on Spring Boot.

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

# Interacting with server

To see list of available services you may open following url: [http://localhost:8080/axis](http://localhost:8080/axis)

If you want to download wsdl file for specific service you may use following url template `http://localhost:8080/axis/<SERVICE_NAME>?wsdl`, i.e. to get wsdl for Version service you should use [http://localhost:8080/axis/Version?wsdl](http://localhost:8080/axis/Version?wsdl).

Axis 1 comes with AdminClient which you may use to deploy wsdd files. For details see [Axis User's Guide](http://axis.apache.org/axis/java/user-guide.html#Using_the_AdminClient).
