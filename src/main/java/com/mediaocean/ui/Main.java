package com.mediaocean.ui;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Logger apacheLogger = java.util.logging.Logger.getLogger("org.apache.http.client.protocol.ResponseProcessCookies");

    public static void main(String[] args) {

        //To Suppress Irrelevant Warnings
        apacheLogger.setLevel(Level.OFF);

        String getApiUri = "http://dummy.restapiexample.com/api/v1/employees";
        String postApiUri = "http://dummy.restapiexample.com/api/v1/create";

        String getResponse = getCall(getApiUri);
        System.out.println("Get Response:\n" + getResponse);
        String postResponse = postCall(postApiUri);
        System.out.println("Post Response:\n" + postResponse);

    }

    static private String getCall(String uri) {
        //String paramString = "someParameterString";

        //Client is Executed for REST Call
        HttpClient client = HttpClients.createDefault();

        try {
            //Build Appropriate URI using Apache Utils
            URIBuilder builder = new URIBuilder(uri);
            //Request Params can be added if required.
            //builder.addParameter('paramName', paramString);

            //Build URI After adding Parameters
            String builtUri = builder.build().toString();
            //For GET Method: HttpGet
            HttpGet httpGet = new HttpGet(builtUri);
            //Add Headers to GET Request
            //httpGet.addHeader(HttpHeaders.ACCEPT, "application/json");

            //Execute client for REST Call
            HttpResponse httpResponse = client.execute(httpGet);

            //Check StatusCode for Success (200)
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode < 200 || statusCode >= 300) {
                //Handle Status Code other than 2XX here
                System.out.println("Request Did Not Succeed");
                return "Status:" + statusCode;
            }

            //Get Response Body
            //Print/ Return Response
            return EntityUtils.toString(httpResponse.getEntity());

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

        return "Exception Occured";
    }

    static private String postCall(String uri) {
        //String paramString = "someParameterString";

        //Client is Executed for REST Call
        HttpClient client = HttpClients.createDefault();

        try {
            //Build Appropriate URI using Apache Utils
            URIBuilder builder = new URIBuilder(uri);
            //Request Params can be added if required.
            //builder.addParameter('paramName', paramString);

            //Build URI After adding Parameters
            String builtUri = builder.build().toString();
            //For Post Method: HttpPost
            HttpPost httpPost = new HttpPost(builtUri);
            //Add Headers to Post Request
            //httpPost.addHeader(HttpHeaders.ACCEPT, "application/json");

            String jsonBody = "{\"name\":\"Prashant\",\"salary\":\"123\",\"age\":\"22\"}";
            StringEntity entity = new StringEntity(jsonBody);

            //Add body to Post Request
            httpPost.setEntity(entity);

            //Execute client for REST Call
            HttpResponse httpResponse;
            httpResponse = client.execute(httpPost);

            //Check StatusCode for Success (200)
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode < 200 || statusCode >= 300) {
                //Handle Status Code other than 2XX here
                System.out.println("Request Did Not Succeed");
                return "Status:" + statusCode;
            }

            //Get Response Body
            //Print/ Return Response
            return EntityUtils.toString(httpResponse.getEntity());

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

        return "Exception Occured";
    }

}
