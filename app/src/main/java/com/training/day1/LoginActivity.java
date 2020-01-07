package com.training.day1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.training.day1.API.RestProcess;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {


    String var_username, var_password;

    EditText etUsername, etPassword;
    Button btnlogin;

    RestProcess restProcess;
    ArrayList<HashMap<String, String>> arrayLogin = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnlogin = findViewById(R.id.btnLogin);

        restProcess = new RestProcess();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                var_username = etUsername.getText().toString();
                var_password = etPassword.getText().toString();

                if (var_username.length() == 0) {
                    Toast.makeText(LoginActivity.this, "username harus di isi", Toast.LENGTH_SHORT).show();
                } else if (var_password.length() == 0) {
                    Toast.makeText(LoginActivity.this, "password harus di isi", Toast.LENGTH_SHORT).show();
                } else if (var_password.length() < 4) {
                    Toast.makeText(LoginActivity.this, "Password kurang dari 4", Toast.LENGTH_SHORT).show();
                } else {
                    loginProcess(view);
                }

            }
        });

        TextView forgotpasstxt = (TextView) findViewById(R.id.txtForgotPass);
        forgotpasstxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }

        });
    }

    private void loginProcess(final View view) {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("var_cell_phone", var_username);
        params.put("var_password", var_password);

        String base_url = restProcess.apiSettingLocal().get("str_ws_addr").toString();
        String auth_user = restProcess.apiSettingLocal().get("str_ws_user").toString();
        String auth_password = restProcess.apiSettingLocal().get("str_ws_pass").toString();

        if (!base_url.equals("") && base_url != null) {
            base_url = base_url + "/auth";
        }

        if (!auth_user.equals("") && auth_user != null && !auth_password.equals("") && auth_password != null) {
            client.setBasicAuth(
                    auth_user,
                    auth_password
            );
        }

        client.post(base_url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String resp_content = "";

                try {
                    resp_content = new String(responseBody, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    displayLogin(view, resp_content);
                } catch (Throwable t) {
                    Toast.makeText(LoginActivity.this, "Koneksi gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void displayLogin(View view, String resp_content) {
        arrayLogin = restProcess.getJsonData(resp_content);
        if (arrayLogin.get(0).get("var_result").equals("1")) {

            startActivity(new Intent(this, MainActivity.class));
        } else if (arrayLogin.get(0).get("var_result").equals("0")) {
            Toast.makeText(LoginActivity.this, "koneksi gagal", Toast.LENGTH_SHORT).show();
        }
    }

}
