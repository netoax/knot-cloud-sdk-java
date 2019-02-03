package br.org.cesar.knot.client;

public class KNoTWebSocketClient {

	private String hostname;
	private String port;
	private String id;
	private String token;
	
	public KNoTWebSocketClient(String hostname, String port, String id, String token) {
		this.setHostname(hostname);
		this.setPort(port);
		this.setId(id);
		this.setToken(token);
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
