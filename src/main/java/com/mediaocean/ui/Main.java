package com.mediaocean.ui;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {

        String apiUri = "http://dummy.restapiexample.com/api/v1/employees";
        //String paramString = "someParameterString";

        HttpClient client = HttpClients.createDefault();

        try {
            URIBuilder builder = new URIBuilder(apiUri);
            //builder.addParameter('paramName', paramString);

            //Build URI After adding Parameters
            String builtUri = builder.build().toString();
            //For GET Method: HttpGet
            HttpGet httpGet = new HttpGet(builtUri);

            //Execute REST Call
            HttpResponse httpResponse = client.execute(httpGet);

            //Check StatusCode for Success (200)
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode < 200 || statusCode >= 300) {
                //Handle Status Code other than 2XX here
                return;
            }
            //Get Response Body
            String responseBody = EntityUtils.toString(httpResponse.getEntity());

            System.out.println("Response:\n" + responseBody);

        }
        catch (URISyntaxException e) {
            //Trigger when URI Builder Fails
            e.printStackTrace();
        }
        catch (ClientProtocolException e) {
            //Trigger when client Execute Fails
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}