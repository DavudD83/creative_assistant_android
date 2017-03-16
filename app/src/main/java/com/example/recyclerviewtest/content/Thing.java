package com.example.recyclerviewtest.content;


import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Thing extends RealmObject{

    @SerializedName("actions")
    private RealmList<RealmString> mActions;

    @SerializedName("description")
    private String mDescription;

    @PrimaryKey
    @SerializedName("id")
    private String mId;

    @SerializedName("is_active")
    private Boolean mIsActive;

    @SerializedName("is_available")
    private Boolean mIsAvailable;

    @SerializedName("placement")
    private String mPlacement;

    @SerializedName("state")
    private String mState;

    @SerializedName("type")
    private String mType;

    public Thing() {
    }

    public Thing(@NonNull RealmList<RealmString> actions, @NonNull String description, @NonNull String id,
                 @NonNull Boolean isActive, @NonNull Boolean isAvailable, @NonNull String placement,
                 @NonNull String state, @NonNull String type) {
        mActions = actions;

        mDescription = description;

        mId = id;

        mIsActive = isActive;

        mIsAvailable = isAvailable;

        mPlacement = placement;

        mState = state;

        mType = type;
    }

    @NonNull
    public RealmList<RealmString> getActions() { return mActions; }

    @NonNull
    public String getDescription() { return mDescription; }

    @NonNull
    public String getId() { return mId; }

    @NonNull
    public Boolean getIsActive() { return mIsActive; }

    @NonNull
    public Boolean getIsAvailable() { return  mIsAvailable; }

    @NonNull
    public String getPlacement() { return mPlacement; }

    @NonNull
    public String getState() { return mState; }

    @NonNull
    public String getType() { return mType; }
}
