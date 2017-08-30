/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.test.soap.client;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class Application {
    public static void main(String[] params) {
        try {
            String endpoint =
                "http://localhost:8080/axis";

            Service  service = new Service();
            Call     call    = (Call) service.createCall();

            call.setTargetEndpointAddress( new java.net.URL(endpoint) );

            String ret = (String) call.invoke("service-managed-by-spring-di", "helloWorld", null);

            System.out.println("Got '" + ret + "'");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
