package com.malviya.loginpagethroughsqlitedatabase.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Prafulla on 1/8/2017.
 */

 class LoginDataModel implements Parcelable {
    private String id;
    private String userName;
    private String password;
    private String email;
    private String mobile;
    private String address;

    private LoginDataModel(Parcel in) {
        id = in.readString();
        userName = in.readString();
        password = in.readString();
        email = in.readString();
        mobile = in.readString();
        address = in.readString();
    }

    public static final Creator<LoginDataModel> CREATOR = new Creator<LoginDataModel>() {
        @Override
        public LoginDataModel createFromParcel(Parcel in) {
            return new LoginDataModel(in);
        }

        @Override
        public LoginDataModel[] newArray(int size) {
            return new LoginDataModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }


    public LoginDataModel(String userName, String password, String email, String mobile, String address) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(userName);
        parcel.writeString(password);
        parcel.writeString(email);
        parcel.writeString(mobile);
        parcel.writeString(address);
    }
}
