package br.org.cesar.knot.client.core;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocketConnection extends WebSocketClient{
	
	public WebSocketConnection(URI serverUri) {
		super(serverUri);
	}

	public void startConnection() {
		super.connect();
	}

	public void disconnect() {
		super.close();
	}

	public void sendMessage(String message) {
		super.send(message);
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		System.out.println("Connection established!");
	}

	@Override
	public void onMessage(String message) {		
		// TODO: send message to the client
		System.out.println(message);
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		// TODO: handle disconnection
	}

	@Override
	public void onError(Exception ex) {
		// TODO: send error to the client
	}

}
