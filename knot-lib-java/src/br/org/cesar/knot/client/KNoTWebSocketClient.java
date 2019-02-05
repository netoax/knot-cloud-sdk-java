package br.org.cesar.knot.client;

import br.org.cesar.knot.client.core.KNoTBasicOperation;
import br.org.cesar.knot.client.core.KNoTWebSocketImpl;

public class KNoTWebSocketClient {

	private String hostname;
	private String port;
	private String id;
	private String token;
	
	public KNoTWebSocketClient(String hostname, String port, String id, String token) {
		this.hostname = hostname;
		this.port = port;
		this.id = id;
		this.token = token;
	}
	
	public void connect() {
		KNoTBasicOperation client = new KNoTWebSocketImpl(this.hostname, this.port, this.id, this.token);
		client.connect();
	}
}
