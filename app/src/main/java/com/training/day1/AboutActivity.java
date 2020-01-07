package com.training.day1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.training.day1.API.RestProcess;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class AboutActivity extends AppCompatActivity {

   WebView webView;
   RestProcess restProcess;
   AsyncHttpClient asyncHttpClient;

    ArrayList<HashMap<String, String>> dataAbout_arrayList = new ArrayList<HashMap<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        webView = findViewById(R.id.wv_About);
        restProcess = new RestProcess();
        HashMap<String, String> apiData = new HashMap<String, String>();
        apiData = restProcess.apiSettingLocal();

        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.setBasicAuth(apiData.get("str_ws_user"), apiData.get("str_ws_pass"));

        asyncHttpClient.post(apiData.get("str_ws_addr").toString() + "/about_apps", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String content = null;
                try {
                    content = new String(responseBody, "UTF-8");
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }

                ArrayList<HashMap<String, String>> arrayList = restProcess.getJsonData(content);
                webView.loadData(arrayList.get(1).get("about_apps").toString(), "text/html", null);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
