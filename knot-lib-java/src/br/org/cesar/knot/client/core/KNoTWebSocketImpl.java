package br.org.cesar.knot.client.core;

import java.net.URI;
import java.net.URISyntaxException;

import com.google.gson.Gson;

public class KNoTWebSocketImpl implements KNoTBasicOperation {

	private URI serverUri;
	private String id;
	private String token;
	private WebSocketConnection connection;

	public KNoTWebSocketImpl(URI serverUri) {
		this.serverUri = serverUri;
	}

	public KNoTWebSocketImpl(String hostname, String port, String id, String token) {
		this.id = id;
		this.token = token;

		try {
			this.serverUri = new URI("ws://" + hostname + ":" + port);
		} catch (URISyntaxException e) {

		}
	}

	@Override
	public void connect() {
		this.connection = new WebSocketConnection(this.serverUri);

		try {
			this.connection.connectBlocking();
			this.identity(this.id, this.token);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void identity(String id, String token) {
		Frame frame = new Frame("identity");
		frame.addValueToData("uuid", id);
		frame.addValueToData("token", token);
		this.sendMessage(frame);
	}

	@Override
	public void registerDevice(String type, String name) {
		Frame frame = this.createRegisterFrame(type, name);
		this.sendMessage(frame);
	}

	@Override
	public void registerDevice(String type, String name, String id) {
		Frame frame = this.createRegisterFrame(type, name);
		frame.addValueToData("ie", id);
		this.sendMessage(frame);
	}
	
	private Frame createRegisterFrame(String type, String name) {
		Frame frame = new Frame("register");
		frame.addValueToData("type", type);		
		frame.addValueToData("name", name);
		return frame;
	}

	private void sendMessage(Frame frame) {
		Gson gson = new Gson();
		String message = gson.toJson(frame);
		this.connection.sendMessage(message);
	}
	

	@Override
	public void getDevices() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMetadata() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDevice() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activateDevice() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createSessionToken() {
		// TODO Auto-generated method stub
		
	}

}
