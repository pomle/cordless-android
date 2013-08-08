package se.cordless.player.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class State {

	Context context;
	
	public State(final Context context) {
		this.context = context;
	}
	
	public SharedPreferences getPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(this.context);
	}
	
	public APIInterface getAPI() {
		
	  	SharedPreferences settings = this.getPreferences();
   		
   		Boolean useHTTPS = settings.getBoolean("use_https", false);
    	String hostAddress = settings.getString("host_address", "");

    	String baseURL = "";
    	if (useHTTPS) {
    		baseURL = "https://";
    	}
    	else {
    		baseURL = "http://";
    	}
    	
    	baseURL = baseURL + hostAddress + "/api/"; 

    	String accessToken = settings.getString("access_token", "");
    	
    	if (0 == accessToken.length()) {
    		return new APIInterface(baseURL);
    	}
    	else {
    		return new APIInterface(baseURL, accessToken);
    	}
	}
}
