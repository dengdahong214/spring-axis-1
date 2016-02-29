/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.test.soap.client;

import javax.xml.namespace.QName;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/**
 *
 * @author plks
 */
public class Application {
    public static void main(String[] params) {
        try {
            String endpoint =
                "http://localhost:8080/";

            Service  service = new Service();
            Call     call    = (Call) service.createCall();

            call.setTargetEndpointAddress( new java.net.URL(endpoint) );
            //call.setOperationName("getVersion");

            //String ret = (String) call.invoke( new Object[] { "Hello!" } );
            String ret = (String) call.invoke("hello-world", "helloWorld", null);

            System.out.println("Got '" + ret + "'");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
