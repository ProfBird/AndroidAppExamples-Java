package edu.uoregon.bbird.weatherdemo;


import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by Brian Bird on 7/17/17.
 * Code adapted from:
 * http://www.techrepublic.com/blog/software-engineer/calling-restful-services-from-your-android-app/
 */



public class RestTask extends AsyncTask<String, Void, String>  {

    // TODO:Replace deprecated HTTPEntity with a more up-to-date class

    @Override
    protected String doInBackground(String... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        String apiKey = "0e3e69302fba4e56";  // put your own API key here
        // Get a free API key at: https://www.wunderground.com/?apiref=5cdccc9428586099
        String baseUri = "http://api.wunderground.com/api/";
        String state = params[0];
        String city = params[1];
        String query = "/forecast/q/" + state + "/" + city + ".xml";
        String uri = baseUri + apiKey + query;
            HttpGet httpGet = new HttpGet(uri);
        String text = null;
        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);


            HttpEntity entity = response.getEntity();

            // Call our helper method to extract a string from the response
            text = getASCIIContentFromEntity(entity);


        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
        return text;
    }

    protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
        InputStream in = entity.getContent();


        StringBuffer out = new StringBuffer();
        int n = 1;
        while (n > 0) {
            byte[] b = new byte[4096];
            n = in.read(b);


            if (n > 0) out.append(new String(b, 0, n));
        }

        return out.toString();
    }

    @Override
    protected void onPostExecute(String results) {
        if (results != null) {
            //EditText et = (EditText)findViewById(R.id.my_edit);


        }
    }
}
