package com.training.day1;

import android.os.Parcel;
import android.os.Parcelable;

public class OfficeModel implements Parcelable {
    String office_name, office_address, office_description, cell_phone, email, location_gps, base_url;

    public OfficeModel(){}

    protected OfficeModel(Parcel in) {
        office_name = in.readString();
        office_address = in.readString();
        office_description = in.readString();
        cell_phone = in.readString();
        email = in.readString();
        location_gps = in.readString();
        base_url = in.readString();
    }

    public static final Creator<OfficeModel> CREATOR = new Creator<OfficeModel>() {
        @Override
        public OfficeModel createFromParcel(Parcel in) {
            return new OfficeModel(in);
        }

        @Override
        public OfficeModel[] newArray(int size) {
            return new OfficeModel[size];
        }
    };

    public String getOffice_name() {
        return office_name;
    }

    public void setOffice_name(String office_name) {
        this.office_name = office_name;
    }

    public String getOffice_address() {
        return office_address;
    }

    public void setOffice_address(String office_address) {
        this.office_address = office_address;
    }

    public String getOffice_description() {
        return office_description;
    }

    public void setOffice_description(String office_description) {
        this.office_description = office_description;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getEmai() {
        return email;
    }

    public void setEmai(String emai) {
        this.email = emai;
    }

    public String getLocation_gps() {
        return location_gps;
    }

    public void setLocation_gps(String location_gps) {
        this.location_gps = location_gps;
    }

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(office_name);
        parcel.writeString(office_address);
        parcel.writeString(office_description);
        parcel.writeString(cell_phone);
        parcel.writeString(email);
        parcel.writeString(location_gps);
        parcel.writeString(base_url);
    }
}
