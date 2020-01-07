package com.training.day1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.training.day1.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ListAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private int list_position = 0;
    private static LayoutInflater inflater = null;
    private String PACKAGE_NAME;

    public ListAdapter(Activity a, ArrayList<HashMap<String, String>> d, int list_pos) {
        data = d;
        activity = a;
        list_position = list_pos;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PACKAGE_NAME = activity.getPackageName();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        View vi = convertview;

        switch (list_position) {
            case 1:
                if (convertview == null)
                    vi = inflater.inflate(R.layout.item_list, null);
                TextView tvEmployeeName = (TextView) vi.findViewById(R.id.tvEmployeeName);
                TextView tvEmployeeNip = (TextView) vi.findViewById(R.id.tvEmployeeNip);
                TextView tvEmployeeAdrress = (TextView) vi.findViewById(R.id.tvEmployeeAdress);
                TextView tvEmployeeGender = (TextView) vi.findViewById(R.id.tvEmployeeGender);
                ImageView img_employee = (ImageView) vi.findViewById(R.id.img_employee);

                HashMap<String, String> empList = new HashMap<String, String>();

                empList = data.get(position);

                tvEmployeeName.setText(empList.get("employee_name"));
                tvEmployeeNip.setText(empList.get("nomor_induk_pegawai"));
                tvEmployeeAdrress.setText(empList.get("address"));
                tvEmployeeGender.setText(empList.get("gender"));
                Picasso.get().load(empList.get("base_url")).into(img_employee);
                break;
            case 2:
                if (convertview==null)
                    vi=inflater.inflate(R.layout.lv_kantor,null);
                TextView tv_name = vi.findViewById(R.id.tv_name);
                TextView tv_email = vi.findViewById(R.id.tv_email);
                TextView tv_cellphone = vi.findViewById(R.id.tv_cellphone);
                ImageView img_kantor = (ImageView) vi.findViewById(R.id.iv_image);
//                    TextView tv_address = vi.findViewById(R.id.tv_address);
//                    TextView tv_desc = vi.findViewById(R.id.tv_desc);

                HashMap<String,String> kntrList = new HashMap<String, String>();
                kntrList = data.get(position);

                Picasso.get().load(kntrList.get("base_url")).into(img_kantor);
                tv_name.setText(kntrList.get("office_name"));
                tv_email.setText(kntrList.get("email"));
                tv_cellphone.setText(kntrList.get("cell_phone"));
//                    tv_address.setText(empList.get("office_address"));
//                    tv_desc.setText(empList.get("office_description"));
                break;
            default:

                break;
        }
        return vi;
    }
}
