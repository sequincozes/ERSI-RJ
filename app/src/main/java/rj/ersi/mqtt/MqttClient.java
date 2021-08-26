package rj.ersi.mqtt;

import com.google.iot.coap.Client;
import com.google.iot.coap.HostLookupException;
import com.google.iot.coap.LocalEndpointManager;
import com.google.iot.coap.Message;
import com.google.iot.coap.RequestBuilder;
import com.google.iot.coap.UnsupportedSchemeException;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt3.Mqtt3BlockingClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3Client;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class MqttClient {
    Mqtt3BlockingClient client;

    public MqttClient(String uri) {
        client = Mqtt3Client.builder()
                .identifier(UUID.randomUUID().toString())
                .serverHost(uri)
                .buildBlocking();
        client.connect();
    }

    public String publishMessage(String msg) {
        long beginFullTime = System.currentTimeMillis();
        client.publishWith().topic("test/topic").qos(MqttQos.AT_LEAST_ONCE)
                .payload(msg.getBytes()).send();
        long fullTime = System.currentTimeMillis() - beginFullTime;
        client.disconnect();
        return "Message sent (" +fullTime + "ms)!";
    }
}




