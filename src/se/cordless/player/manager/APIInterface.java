package se.cordless.player.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import se.cordless.player.manager.api.Request;
import se.cordless.player.manager.api.Response;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import android.util.Log;

public class APIInterface {

	public String baseURL;
	public String accessToken;
	
	public APIInterface(String baseURL, String accessToken) {
		this.accessToken = accessToken;
		this.baseURL = baseURL;
	}

	public APIInterface(String baseURL) {
		this(baseURL, "");
	}

	public Response sendRequest(Request request) {
		
		StringBuilder builder = new StringBuilder();
		
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(this.baseURL + "method=" + request.method);
			
			List<NameValuePair> data = new ArrayList<NameValuePair>(1);
			data.add(new BasicNameValuePair("params", request.getSerializedPayload()));
				
			httpPost.setEntity(new UrlEncodedFormEntity(data));
	
			HttpResponse response = client.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
			    while ((line = reader.readLine()) != null) {
			    	builder.append(line);
			    }
			}
		}
		catch(IOException e) {
			Log.e("OMG", "ERROR");
		}
		
	    return new Response(builder.toString());
	}
}