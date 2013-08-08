package se.cordless.player.manager.api;

import org.json.JSONException;
import org.json.JSONObject;

public class Response {

	private JSONObject payload;
	
	public Response(String json) throws JSONException {
		this.payload = new JSONObject(json);
	}
	
	public JSONObject getPayload() throws JSONException {
		return this.payload.getJSONObject("data");
	}
}
