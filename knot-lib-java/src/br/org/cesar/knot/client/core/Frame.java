package br.org.cesar.knot.client.core;

import java.util.HashMap;
import java.util.Map;

public class Frame {

	private String type;
	private Map<String,Object> data = new HashMap<String, Object>();

	public Frame(String type) {
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void addValueToData(String key, Object value) {
		this.data.put(key, value);
	}
}
