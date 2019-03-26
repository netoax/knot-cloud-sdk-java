package br.org.cesar.knot.client.core;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.Gson;

public class KNoTWebSocketImpl implements KNoTBasicOperation {

	private URI serverUri;
	private String id;
	private String token;
	private WebSocketConnection connection;

	HashMap<String, ArrayList<KNoTCloudMessage>> eventListeners = new HashMap<String, ArrayList<KNoTCloudMessage>>();

	public KNoTWebSocketImpl(URI serverUri) {
		this.serverUri = serverUri;
	}


	public KNoTWebSocketImpl(String hostname, String port, String id, String token) {
		this.id = id;
		this.token = token;

		try {
			this.serverUri = new URI("wss://" + hostname + ":" + port);
		} catch (URISyntaxException e) {
			// Do nothing
		}

		this.initializeListeners();
	}

	private void initializeListeners() {
		this.eventListeners.put("ready", new ArrayList<KNoTCloudMessage>());
		this.eventListeners.put("error", new ArrayList<KNoTCloudMessage>());
		this.eventListeners.put("registered", new ArrayList<KNoTCloudMessage>());
		this.eventListeners.put("unregistered", new ArrayList<KNoTCloudMessage>());
		this.eventListeners.put("devices", new ArrayList<KNoTCloudMessage>());
		this.eventListeners.put("created", new ArrayList<KNoTCloudMessage>());
		this.eventListeners.put("revoked", new ArrayList<KNoTCloudMessage>());
		this.eventListeners.put("updated", new ArrayList<KNoTCloudMessage>());
		this.eventListeners.put("activated", new ArrayList<KNoTCloudMessage>());
		this.eventListeners.put("published", new ArrayList<KNoTCloudMessage>());
		this.eventListeners.put("sent", new ArrayList<KNoTCloudMessage>());

	}

	private void evokeEventListeners(String type, String data) {
		ArrayList<KNoTCloudMessage> listeners = eventListeners.get(type);
		for (KNoTCloudMessage listener: listeners) {
			listener.on(data);
		}
	}

	private class EventHandle implements KNoTCloudMessage {
		@Override
		public void on(String message) {
			JSONObject jsonMessage = new JSONObject(message);
			String type = jsonMessage.getString("type");
			evokeEventListeners(type, message);
		}
	}

	@Override
	public void connect() {
		this.connection = new WebSocketConnection(this.serverUri);

		try {
			this.connection.connectBlocking();
			this.identity(this.id, this.token);
			this.connection.registerMessageListener(new EventHandle());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void on(String type, KNoTCloudMessage messageListener) {
		ArrayList<KNoTCloudMessage> listeners = this.eventListeners.get(type);
		listeners.add(messageListener);
		this.eventListeners.put(type, listeners);
	}

	private void identity(String id, String token) {
		Frame frame = new Frame("identity");
		frame.addValueToData("id", id);
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
		frame.addValueToData("id", id);
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
	public String getDevices() {
		Frame frame = new Frame("devices");
		this.sendMessage(frame);
		return "";
	}

	@Override
	public void updateMetadata(String id, String propName, String value) {
		Frame frame = new Frame("metadata");
		Map<String,String> data = new HashMap<String,String>();
		data.put(propName, value);
		frame.addValueToData("id", id);
		frame.addValueToData("metadata", data);
		this.sendMessage(frame);
	}

	@Override
	public void publishData(String sensorId, String value) {
		Frame frame = new Frame("data");
		frame.addValueToData("sensorId", sensorId);
		frame.addValueToData("value", value);
		this.sendMessage(frame);
	}

	@Override
	public void unregisterDevice(String id) {
		Frame frame = new Frame("unregister");
		frame.addValueToData("id", id);
		this.sendMessage(frame);
	}

	@Override
	public void activateDevice(String id) {
		Frame frame = new Frame("token");
		frame.addValueToData("id", id);
		this.sendMessage(frame);
	}

	@Override
	public void createSessionToken(String id) {
		Frame frame = new Frame("token");
		frame.addValueToData("id", id);
		this.sendMessage(frame);
	}


	@Override
	public void getData(String id, int[] sensorIds) {
		Frame frame = new Frame("getData");
		frame.addValueToData("id", id);
		frame.addValueToData("sensorIds", sensorIds);
		this.sendMessage(frame);
	}


	@Override
	public void setData(String id, String data) {
		Frame frame = new Frame("setData");
		frame.addValueToData("id", id);
		frame.addValueToData("data", data);
		this.sendMessage(frame);
	}


	@Override
	public void revokeSessionToken(String id, String token) {
		Frame frame = new Frame("revoke");
		frame.addValueToData("id", id);
		frame.addValueToData("token", token);
		this.sendMessage(frame);
	}


	@Override
	public void updateSchema(String schema) {
		Frame frame = new Frame("schema");
		frame.addValueToData("schema", schema);
		this.sendMessage(frame);
	}
}
