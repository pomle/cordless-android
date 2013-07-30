package se.cordless.player;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

import se.cordless.player.manager.APIInterface;

public class MainActivity extends Activity {

	public APIInterface api = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
   		SharedPreferences settings = getPreferences(0);
   		
   		Boolean useHTTPS = settings.getBoolean("use_https", false);
    	String hostAddress = settings.getString("host_address", "");
    	String accessToken = settings.getString("access_token", "");
    	
    	if (hostAddress.isEmpty()) {
    		Intent intent = new Intent(this, SettingsActivity.class);
    		startActivity(intent);
    	}
    	
    	String baseURL = "";
    	if (useHTTPS) {
    		baseURL = "https://";
    	}
    	else {
    		baseURL = "http://";
    	}
    	
    	baseURL = baseURL + hostAddress + "/api/";

    	if (accessToken.isEmpty()) {
    		Intent intent = new Intent(this, LoginActivity.class);
    		startActivity(intent);
    	}
        
        api = new APIInterface(baseURL, accessToken);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) 
    	{
    		case R.id.action_settings:
    			startActivity(new Intent(this, SettingsActivity.class));
    		break;
    	}
        return false;
    }
}
