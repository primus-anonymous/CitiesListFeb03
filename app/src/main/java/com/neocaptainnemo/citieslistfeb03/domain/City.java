package com.neocaptainnemo.citieslistfeb03.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;

public class City implements Parcelable {
    private final String id;

    @DrawableRes
    private final int image;

    private final String name;

    public City(String id, int image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    protected City(Parcel in) {
        id = in.readString();
        image = in.readInt();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(image);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public String getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
