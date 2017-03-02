package com.example.recyclerviewtest.content;


import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("type")
    private String mType;

    @SerializedName("source")
    private String mSource;

    @SerializedName("event")
    private String mEvent;

    @SerializedName("body")
    private Body mBody;


    public Message(Body body) {

        this.mType = "user_request";

        this.mSource = "android";

        this.mEvent = "action_requested";

        this.mBody = body;
    }


}
