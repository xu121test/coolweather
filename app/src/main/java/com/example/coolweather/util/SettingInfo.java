package com.example.coolweather.util;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class SettingInfo implements Parcelable {
    private boolean isAllowUpdate;
    private int update;

    public SettingInfo() {
    }

    public SettingInfo(int update) {
        this.update = update;
    }

    public SettingInfo(boolean isAllowUpdate) {
        this.isAllowUpdate = isAllowUpdate;
    }

   /* public SettingInfo(boolean isAllowUpdate, int update) {
        this.isAllowUpdate = isAllowUpdate;
        this.update = update;
    }*/

    public int getUpdate() {
        return update;
    }

    public boolean isAllowUpdate() {
        return isAllowUpdate;
    }

    public void setAllowUpdate(boolean allowUpdate) {
        this.isAllowUpdate = allowUpdate;
    }

    public void setUpdate(int update) {
        this.update = update;
    }


    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void writeToParcel(Parcel out, int flag) {
        out.writeBoolean(isAllowUpdate);
        out.writeInt(update);
    }

    public static final Creator<SettingInfo> CREATOR = new Creator<SettingInfo>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public SettingInfo createFromParcel(Parcel parcel) {
            SettingInfo settingInfo = new SettingInfo();
            settingInfo.setAllowUpdate(parcel.readBoolean());
            settingInfo.setUpdate(parcel.readInt());
            return settingInfo;
        }

        @Override
        public SettingInfo[] newArray(int i) {
            return new SettingInfo[i];
        }
    };

}
