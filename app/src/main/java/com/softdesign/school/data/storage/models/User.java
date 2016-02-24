package com.softdesign.school.data.storage.models;


import android.graphics.drawable.Drawable;

public class User {
    private String mFirstName;
    private String mLastName;
    private int mRait;
    private Drawable mImage;
    private String mVkLink;
    private String mGitLing;
    private int mHomeTask;

    public User(Drawable mImage, String mFirstName, String mLastName) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mImage = mImage;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public int getmRait() {
        return mRait;
    }

    public Drawable getmImage() {
        return mImage;
    }

    public String getmVkLink() {
        return mVkLink;
    }

    public String getmGitLing() {
        return mGitLing;
    }

    public int getmHomeTask() {
        return mHomeTask;
    }
}
