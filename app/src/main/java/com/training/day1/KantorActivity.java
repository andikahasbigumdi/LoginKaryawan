package com.training.day1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.training.day1.API.RestProcess;
import com.training.day1.Adapter.ListAdapter;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class KantorActivity extends AppCompatActivity {
    private RestProcess rest_class;
    ListView lvDataKantor;
    ListAdapter adapter;
    ArrayList<HashMap<String, String>> datakantor_arraylist = new ArrayList<HashMap<String, String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kantor);

        rest_class = new RestProcess();

        lvDataKantor = (ListView) findViewById(R.id.lv_kantor);
        getDataKantor(null);
    }

    private void getDataKantor(final View view) {
        HashMap<String, String> apiData = new HashMap<String, String>();
        apiData = rest_class.apiSettingLocal();
        AsyncHttpClient client = new AsyncHttpClient();
        String base_url = apiData.get("str_ws_addr") + "/office";

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
                    displayDataKantor(view, resp_content);
                } catch (Throwable t) {
                    Toast.makeText(KantorActivity.this, "koneksi gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(KantorActivity.this, "koneksi gagal2", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void displayDataKantor(View view, String resp_content) {
        datakantor_arraylist = rest_class.getJsonData(resp_content);
        if (datakantor_arraylist.get(0).get("var_result").toString().equals("1")) {

            datakantor_arraylist.remove(0);
            adapter = new ListAdapter(KantorActivity.this, datakantor_arraylist, 2);
            lvDataKantor.setAdapter(adapter);
            lvDataKantor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    OfficeModel model = new OfficeModel();
                    model.setOffice_name(datakantor_arraylist.get(i).get("office_name"));
                    model.setOffice_address(datakantor_arraylist.get(i).get("office_address"));
                    model.setOffice_description(datakantor_arraylist.get(i).get("office_description"));
                    model.setCell_phone(datakantor_arraylist.get(i).get("cell_phone"));
                    model.setEmai(datakantor_arraylist.get(i).get("email"));
                    model.setLocation_gps(datakantor_arraylist.get(i).get("location_gps"));
                    model.setBase_url(datakantor_arraylist.get(i).get("base_url"));

                    startActivity(new Intent(KantorActivity.this, OfficeDetailActivity.class).putExtra("DATA_OFFICE", model));
                }
            });
            Toast.makeText(KantorActivity.this, "koneksi berhasil", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(KantorActivity.this, "koneksi gagal", Toast.LENGTH_SHORT).show();
        }
    }

}
