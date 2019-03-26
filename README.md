# knot-lib-java

A client side library to facilitate the KNoT Cloud usage for Java applications.

# Quickstart

### Minimum Requirements

To use this library, you will need Java 1.8+.

# How to use the library

### Initialize the Client

To use this library, you need to establish a WebSocket connection with the KNoT Cloud by initializing the `KNoTWebSocketClient`. It requires just the cloud server address and the credentials that you will use to authenticate on behalf of some device.

	String hostname = "ws.knot.cloud";
	String port = "443";
	String credential_id = "4defaa29-0201-474f-a8f9-afe7fef0e8bd";
	String credential_token = "b1e00acc4a25a5818e648ab867dd51dc86c288a6";

	KNoTWebSocketClient client = new KNoTWebSocketClient(hostname, port, credential_id, credential_token);
	client.connect();

### Publish and Receive Messages

In order to publish a message you just need to call the `publishData(sensorId, data)` method passing the id of the sensor you want to send messages and the data itself.

	client.publishMessage(1, true);

On the other hand, to receive messages from the devices you need to call the `on(eventType, eventListener)` method informing the type of messages you want listen to. The main difference here is that you need to create a event listener to receive these messages and this listener need to implement the `KNoTCloudMessage` interface.

	public class MyEvent implements KNoTCloudMessage {
		@Override
		public void on(String message) {
			// Do whatever you want with the message
			System.out.println(message);
		}
	}

After that, you just need to call the method passing the event type and listener.

	KNoTCloudMessage eventListener = new MyEvent();
	client.on("data", eventListener);
