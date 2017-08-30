package com.jiuzhang.guojing.awesomeresume.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.jiuzhang.guojing.awesomeresume.util.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by kevinzhou on 8/28/17.
 */

public class Experience implements Parcelable{
    public String id;

    public String company;

    public String title;

    public Date startDate;

    public Date endDate;

    public List<String> details;

    protected Experience(Parcel in) {
        id = in.readString();
        company = in.readString();
        title = in.readString();
        startDate = DateUtils.stringToDate(in.readString());
        endDate = DateUtils.stringToDate(in.readString());
        details = in.createStringArrayList();
    }

    public Experience() {
        id = UUID.randomUUID().toString();
    }

    public static final Creator<Experience> CREATOR = new Creator<Experience>() {
        @Override
        public Experience createFromParcel(Parcel in) {
            return new Experience(in);
        }

        @Override
        public Experience[] newArray(int size) {
            return new Experience[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(company);
        parcel.writeString(title);
        parcel.writeString(DateUtils.dateToString(startDate));
        parcel.writeString(DateUtils.dateToString(endDate));
        parcel.writeStringList(details);
    }
}
