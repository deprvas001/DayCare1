package com.development.daycare.model.addDay;

import android.os.Parcel;
import android.os.Parcelable;

public class DayCareMenu implements Parcelable {
    private String menu_name;
    private String menu_description;

    public DayCareMenu(){

    }

    protected DayCareMenu(Parcel in) {
        menu_name = in.readString();
        menu_description = in.readString();
    }

    public static final Creator<DayCareMenu> CREATOR = new Creator<DayCareMenu>() {
        @Override
        public DayCareMenu createFromParcel(Parcel in) {
            return new DayCareMenu(in);
        }

        @Override
        public DayCareMenu[] newArray(int size) {
            return new DayCareMenu[size];
        }
    };

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_description() {
        return menu_description;
    }

    public void setMenu_description(String menu_description) {
        this.menu_description = menu_description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(menu_name);
        parcel.writeString(menu_description);
    }
}
