package com.example.recyclerviewtest.content;

import com.google.gson.annotations.SerializedName;

public class Body {

    @SerializedName("action")
    private String mAction;

    @SerializedName("obj_id")
    private String mId;

    @SerializedName("action_params")
    private ActionParams mActionParams;


    public Body(String action, String id, ActionParams actionParams) {

        mAction = action;
        mId = id;
        mActionParams = actionParams;
    }





}
