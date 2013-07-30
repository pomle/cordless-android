package se.cordless.player.manager.api;

import org.json.JSONException;
import org.json.JSONObject;

public class Request {

	public String method;
	protected JSONObject payload;
	
	public Request(String method) {
		this.method = method;
	}
	
	public void addData(String name, String value) throws JSONException {
		this.payload.put(name, value);
	}
	
	public String getSerializedPayload() {
		return this.payload.toString();
	}
}
