package com.example.recyclerviewtest.content;

import io.realm.RealmObject;

public class RealmString extends RealmObject {

    private String mVar;

    public RealmString() {
    }

    public RealmString(String var) {
        mVar = var;
    }

    public String getVar() { return mVar; }

    public void setVar(String var) { mVar = var;}

}
