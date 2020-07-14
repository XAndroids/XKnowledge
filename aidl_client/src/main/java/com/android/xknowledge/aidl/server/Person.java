package com.android.xknowledge.aidl.server;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    protected Person(Parcel in) {
        this.name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
