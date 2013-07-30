package se.cordless.player.manager.api;

import org.json.JSONException;
import org.json.JSONObject;

public class Response {

	public JSONObject payload;
	
	public Response(String json) throws JSONException {
		this.payload = new JSONObject(json);
	}
	
	public JSONObject getPayload() {
		return this.payload;
	}
}
