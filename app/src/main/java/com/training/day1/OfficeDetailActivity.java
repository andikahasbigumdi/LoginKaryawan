package com.training.day1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class OfficeDetailActivity extends AppCompatActivity {

    TextView office_name, office_address, office_description, cell_phone, email;
    ImageView img;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_detail);

        office_name = findViewById(R.id.txtDetailNama);
        office_address = findViewById(R.id.txtDetailAlamat);
        office_description = findViewById(R.id.txtDetailDeskripsi);
        cell_phone = findViewById(R.id.txtDetailTelpon);
        email = findViewById(R.id.txtDetailEmail);
        img = findViewById(R.id.imgDetailKantor);
        fab = findViewById(R.id.fab);

        final OfficeModel model = getIntent().getParcelableExtra("DATA_OFFICE");

        office_name.setText(model.getOffice_name());
        office_address.setText(model.getOffice_address());
        office_description.setText(model.getOffice_description());
        cell_phone.setText(model.getCell_phone());
        email.setText(model.getEmai());

        Picasso.get().load(model.getBase_url()).into(img);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/" + model.getLocation_gps())));
            }
        });

    }

}
