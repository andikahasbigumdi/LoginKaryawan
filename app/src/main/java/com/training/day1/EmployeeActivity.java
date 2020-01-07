package com.training.day1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.training.day1.API.RestProcess;
import com.training.day1.Adapter.ListAdapter;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class EmployeeActivity extends AppCompatActivity {

    private RestProcess rest_class;
    ListView lvDataKaryawan;
    ListAdapter adapter;
    ArrayList<HashMap<String, String>> datakaryawan_arraylit = new ArrayList<HashMap<String, String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        rest_class = new RestProcess();

        lvDataKaryawan = (ListView) findViewById(R.id.lv_employee);
        getDataKaryawan(null);
    }

    private void getDataKaryawan(final View view) {
        HashMap<String, String> apiData = new HashMap<String, String>();
        apiData = rest_class.apiSettingLocal();
        AsyncHttpClient client = new AsyncHttpClient();
        String base_url = apiData.get("str_ws_addr") + "/employee";

        client.setBasicAuth(apiData.get("str_ws_user"), apiData.get("str_ws_pass"));
        client.post(base_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String resp_content = "";
                try {
                    resp_content = new String(responseBody, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    displayDataKaryawan(view, resp_content);
                } catch (Throwable t) {
                    Toast.makeText(EmployeeActivity.this, "koneksi gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(EmployeeActivity.this, "koneksi gagal2", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void displayDataKaryawan(View view, String resp_content) {
        datakaryawan_arraylit = rest_class.getJsonData(resp_content);
        if (datakaryawan_arraylit.get(0).get("var_result").toString().equals("1")) {

            datakaryawan_arraylit.remove(0);
            adapter = new ListAdapter(EmployeeActivity.this, datakaryawan_arraylit, 1);
            lvDataKaryawan.setAdapter(adapter);
            Toast.makeText(EmployeeActivity.this, "koneksi berhasil", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(EmployeeActivity.this, "koneksi gagal", Toast.LENGTH_SHORT).show();
        }
    }
}

