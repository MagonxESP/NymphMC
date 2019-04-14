package com.magonxesp.nymph.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {

    private URL url;
    private HttpURLConnection request;

    public HttpRequest() {

    }

    public HttpRequest(String url, String method) throws IOException {
        prepareRequestUrl(url, method);
    }

    private void prepareRequestUrl(String url, String method) throws IOException {
        this.url = new URL(url);
        request = (HttpURLConnection) this.url.openConnection();
        request.setRequestMethod(method);
    }

    public void setUrl(String url, String method) throws IOException {
        prepareRequestUrl(url, method);
    }

    public void addHttpHeader(String key, String value) {
        request.setRequestProperty(key, value);
    }

    public void send(String params) throws IOException {
        request.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(request.getOutputStream());
        outputStream.writeBytes(params);
        outputStream.flush();
        outputStream.close();
    }

    public int getResponseCode() throws IOException {
        return request.getResponseCode();
    }

    public String getResponse() throws IOException {
        InputStream inputStream = request.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            response.append(line);
            response.append("\r");
        }

        bufferedReader.close();

        return response.toString();
    }

}
