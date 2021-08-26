package rj.ersi.mqtt;

import android.widget.Toast;

import com.google.iot.coap.Client;
import com.google.iot.coap.HostLookupException;
import com.google.iot.coap.LocalEndpointManager;
import com.google.iot.coap.Message;
import com.google.iot.coap.RequestBuilder;
import com.google.iot.coap.UnsupportedSchemeException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CoAPClient {
    RequestBuilder requestBuilder;

    public CoAPClient(String uri){
    LocalEndpointManager manager = new LocalEndpointManager();
        try {
            Client client = new Client(manager, uri);
            requestBuilder = client.newRequestBuilder().setConfirmable(true);
        } catch (UnsupportedSchemeException e) {
            e.printStackTrace();
        }
    }

    public String sendRequest(){
        Message response = null;
        try {
            long beginFullTime = System.currentTimeMillis();
            response = requestBuilder.send().getResponse();
            long fullTime = System.currentTimeMillis() - beginFullTime;
            return "Response received ("+fullTime+"ms): "+response.getPayloadAsString();

        } catch (InterruptedException | HostLookupException | IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return response.getPayloadAsString();
    };
}
