package SensorProcessor;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;




public class SimpleMqttCallback implements MqttCallback {

    /*
    This method is called when the connection to the server is lost.
     */
    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to MQTT broker lost!");
    }
/*
This method is called when a message arrives from the server.
This method is invoked synchronously by the MQTT client.
An acknowledgment is not sent back to the server until this method returns cleanly.
If an implementation of this method throws an Exception, then the client will be shut down.
When the client is next re-connected, any QoS 1 or 2 messages will be redelivered by the server.
Any additional messages which arrive while an implementation of this method is running,
will build up in memory, and will then back up on the network.
If an application needs to persist data, then it should ensure the data is persisted prior to returning from this method,
as after returning from this method, the message is considered to have been delivered, and will not be reproducible.
It is possible to send a new message within an implementation of this callback (for example, a response to this message),
but the implementation must not disconnect the client, as it will be impossible to send an acknowledgment for the message being processed,
and a deadlock will occur.
 */
    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("Message received: "+ new String(mqttMessage.getPayload()));

    }
/*
Called when delivery for a message has been completed, and all acknowledgments have been received.
For QoS 0 messages it is called once the message has been handed to the network for delivery.
For QoS 1 it is called when PUBACK is received and for QoS 2 when PUBCOMP is received.
The token will be the same token as that returned when the message was published.
 */
    @Override
    public void deliveryComplete(IMqttDeliveryToken MqttDeliveryToken) {

        try {
            System.out.println("Delivery completed: "+ MqttDeliveryToken.getMessage() );
        } catch (MqttException e) {
            System.out.println("Failed to get delivery token message: " + e.getMessage());
        }

    }
}
