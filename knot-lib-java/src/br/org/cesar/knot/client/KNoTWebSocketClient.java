package br.org.cesar.knot.client;

import br.org.cesar.knot.client.core.KNoTBasicOperation;
import br.org.cesar.knot.client.core.KNoTCloudMessage;
import br.org.cesar.knot.client.core.KNoTWebSocketImpl;

public class KNoTWebSocketClient {

	private String hostname;
	private String port;
	private String id;
	private String token;
	private KNoTBasicOperation client;

	public KNoTWebSocketClient(String hostname, String port, String id, String token) {
		this.hostname = hostname;
		this.port = port;
		this.id = id;
		this.token = token;
	}

	public void connect() {
		client = new KNoTWebSocketImpl(this.hostname, this.port, this.id, this.token);
		client.connect();
	}

	public void on(String type, KNoTCloudMessage messageListener) {
		client.on(type, messageListener);
	}

	public void registerDevice(String type, String name) {
		client.registerDevice(type, name);
	}

	public void registerDevice(String type, String name, String id) {
		client.registerDevice(type, name, id);
	}

	public void publishData(String sensorId, String value) {
		client.publishData(sensorId, value);
	}

	public void updateMetadata(String id, String propName, String value) {
		client.updateMetadata(id, propName, value);
	}

	public String getDevices() {
		return client.getDevices();
	}

	public void createSessionToken(String id) {
		client.createSessionToken(id);
	}

	public void unregisterDevice(String id) {
		client.unregisterDevice(id);
	}

	public void activateDevice(String id) {
		client.activateDevice(id);
	}

	public void getData(String id, int[] sensorIds) {
		client.getData(id, sensorIds);
	}

	public void setData(String id, String data) {
		client.setData(id, data);
	}
}
