package br.org.cesar.knot.client.core;

public interface KNoTBasicOperation {

	public void connect();

	public void on(String type, KNoTCloudMessage message);

	public void registerDevice(String type, String name);

	public void registerDevice(String type, String name, String id);

	public void publishData(String sensorId, String value);

	public void getData(String id, int[] sensorIds);

	public void setData(String id, String data);

	public String getDevices();

	public void updateMetadata(String id, String propName, String value);

	public void unregisterDevice(String id);

	public void activateDevice(String id);

	public void createSessionToken(String id);

	public void revokeSessionToken(String id, String token);

	public void updateSchema(String schema);

}
