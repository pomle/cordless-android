package se.cordless.player;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import se.cordless.player.manager.State;

public class MainActivity extends Activity {

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  this.init();
	} 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
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
    
    public Boolean init() {

    	State state = new State(this);
    	SharedPreferences settings = state.getPreferences();
    	
    	String hostAddress = settings.getString("host_address", "");
    	String accessToken = settings.getString("access_token", "");
    	
    	Log.d("host_address", hostAddress);
    	if (hostAddress.isEmpty()) {
    		Intent intent = new Intent(this, SettingsActivity.class);
    		startActivityForResult(intent, 1);
    		return false;
    	}
    	
    	Log.d("access_token", "X" + accessToken);
    	if (accessToken.isEmpty()) {
    		Intent intent = new Intent(this, LoginActivity.class);
    		startActivityForResult(intent, 2);
    		return false;
    	}
    	
    	return true;
    }
}
