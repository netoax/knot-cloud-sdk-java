package src.main.java.br.org.cesar.knot.client.main.java.br.org.cesar.knot.client.core;

public interface KNoTWebSocketOperation {

	public void connect();
	
	public void registerDevice();
	
	public void getDevices();
	
	public void updateMetadata();
	
	public void unregisterDevice();
	
	public void activateDevice();
	
	public void createSessionToken();
	
}
