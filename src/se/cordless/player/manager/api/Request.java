package se.cordless.player.manager.api;

import org.json.JSONException;
import org.json.JSONObject;

public class Request {

	public String method;
	private JSONObject payload;
	
	public Request(String method) {
		this.method = method;
		this.payload = new JSONObject();
	}
	
	public void addData(String name, String value) throws JSONException {
		this.payload.put(name, value);
	}

	public void addData(String name, Boolean value) throws JSONException {
		this.payload.put(name, value);
	}
	
	public String getSerializedPayload() {
		return this.payload.toString();
	}
}
