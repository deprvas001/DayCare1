package com.development.daycare.model.addDay;

import android.os.Parcel;
import android.os.Parcelable;

public class SubjectList implements Parcelable {
private String subject_name;
private String subject_description;

  public SubjectList(){

  }

    protected SubjectList(Parcel in) {
        subject_name = in.readString();
        subject_description = in.readString();
    }

    public static final Creator<SubjectList> CREATOR = new Creator<SubjectList>() {
        @Override
        public SubjectList createFromParcel(Parcel in) {
            return new SubjectList(in);
        }

        @Override
        public SubjectList[] newArray(int size) {
            return new SubjectList[size];
        }
    };

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_description() {
        return subject_description;
    }

    public void setSubject_description(String subject_description) {
        this.subject_description = subject_description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(subject_name);
        parcel.writeString(subject_description);
    }
}

