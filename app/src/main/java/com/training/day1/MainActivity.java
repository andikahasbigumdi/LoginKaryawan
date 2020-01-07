package com.training.day1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {

    Drawer result;
    AccountHeader headerResult;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .withHeaderBackground(R.drawable.header_background)
                .build();
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Beranda").withIcon(GoogleMaterial.Icon.gmd_home).withSelectable(true),
                        new PrimaryDrawerItem().withName("Tentang Developer").withIcon(GoogleMaterial.Icon.gmd_info).withSelectable(true),
                        new PrimaryDrawerItem().withName("Daftar Karyawan").withIcon(GoogleMaterial.Icon.gmd_person).withSelectable(true),
                        new PrimaryDrawerItem().withName("Daftar Office").withIcon(GoogleMaterial.Icon.gmd_work).withSelectable(true),
                        new PrimaryDrawerItem().withName("Logout").withIcon(GoogleMaterial.Icon.gmd_exit_to_app).withSelectable(true)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = null;
                        switch (position) {
                            case 1:
                                break;
                            case 2:
                                intent = new Intent(MainActivity.this, DeveloperActivity.class);
                                startActivity(intent);
                                break;
                            case 3:
                                intent = new Intent(MainActivity.this, EmployeeActivity.class);
                                startActivity(intent);
                                break;
                            case 4:
                                intent = new Intent(MainActivity.this, KantorActivity.class);
                                startActivity(intent);
                                break;
                            case 5:
                                finish();
                                break;
                        }
                        return false;
                    }
                }).build();
    }


//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        Intent intent = null;
//        switch (item.getItemId()) {
//            case R.id.data_karyawan:
//                intent = new Intent(MainActivity.this, EmployeeActivity.class);
//                break;
//            case R.id.tentang_apps:
//                intent = new Intent(MainActivity.this, DeveloperActivity.class);
//                break;
//            case R.id.data_kantor:
//                intent = new Intent(MainActivity.this, KantorActivity.class);
//                break;
//            case R.id.keluar:
//                finish();
//                break;
//        }
//        startActivity(intent);
//        return super.onOptionsItemSelected(item);
//    }
}
