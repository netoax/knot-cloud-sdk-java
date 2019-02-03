package br.org.cesar.knot.client.core;

public class KNoTWebSocketOperationImpl implements KNoTWebSocketOperation {

	public KNoTWebSocketOperationImpl() {
	
	}
	
	@Override
	public void connect() {
		WebSocketConnectionImpl connection = new WebSocketConnectionImpl();
		connection.connect();
	}

	@Override
	public void registerDevice() {
		// TODO Auto-generated method stub
		
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
