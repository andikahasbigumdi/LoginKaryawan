package com.training.day1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {
    String var_passbaru, var_konfirmasi;
    EditText etPasswordBaru, etKonfirmasiPassword;
    Button btnForgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

   etPasswordBaru = findViewById(R.id.etPasswordBaru);
   etKonfirmasiPassword = findViewById(R.id.etKonfirmasiPassword);

   btnForgot = findViewById(R.id.btnForgot);

   btnForgot.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           var_passbaru = etPasswordBaru.getText().toString();
           var_konfirmasi = etKonfirmasiPassword.getText().toString();

           if(var_passbaru.equals("") || var_konfirmasi.equals("")){
               Toast.makeText(ForgotPassword.this, "password tidak boleh kosong", Toast.LENGTH_SHORT).show();

           }else if (var_passbaru.equals(var_konfirmasi)){
               Toast.makeText(ForgotPassword.this, "password berhasil diganti", Toast.LENGTH_SHORT).show();
           }else{
               Toast.makeText(ForgotPassword.this, "pass baru dan lama tidak cocok", Toast.LENGTH_SHORT) .show();
           }

       }
   });
    }
}
