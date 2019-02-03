package src.main.java.br.org.cesar.knot.client.main.java.br.org.cesar.knot.client.core;

interface WebSocketConnection {
	
	public void connect();
	
	public void onConnected();

	public void disconnect();
	
	public void onDisconnected();
	
	public void sendMessage();
	
	public void onMessage();
	
}