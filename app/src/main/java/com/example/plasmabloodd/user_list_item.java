package com.example.plasmabloodd;

import android.os.Parcel;
import android.os.Parcelable;

public class user_list_item implements Parcelable {

    private String user_name;
    private String user_city;
    private int mImageResource;
    private String user_mob;

    public user_list_item(String user_name, String user_city, int ImageResource, String user_mob) {
        this.mImageResource = ImageResource;
        this.user_name = user_name;
        this.user_city = user_city;
        this.user_mob = user_mob;
    }

    protected user_list_item(Parcel in) {
        user_name = in.readString();
        user_city = in.readString();
        mImageResource = in.readInt();
        user_mob = in.readString();
    }

    public static final Creator<user_list_item> CREATOR = new Creator<user_list_item>() {
        @Override
        public user_list_item createFromParcel(Parcel in) {
            return new user_list_item(in);
        }

        @Override
        public user_list_item[] newArray(int size) {
            return new user_list_item[size];
        }
    };

    public String getUser_name() {
        return user_name;
    }
    public String getUser_city() {
        return user_city;
    }
    public int getmImageResource() {
        return mImageResource;
    }
    public String getUser_mob(){return user_mob;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_name);
        dest.writeString(user_city);
        dest.writeInt(mImageResource);
        dest.writeString(user_mob);
    }
}
