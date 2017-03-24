package space.dotcat.assistant.content;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

public class Room extends RealmObject implements Serializable {

    @SerializedName("description")
    private String mDescription;

    @SerializedName("id")
    private String mId;

    @SerializedName("image")
    private String mImagePath;

    public Room() {
    }

    public Room(@NonNull String description, @NonNull String id, @NonNull String imagePath) {
        mDescription = description;

        mId = id;


        mImagePath = imagePath;
    }

    @NonNull
    public String GetId() {
        return mId;
    }

    @NonNull
    public String GetDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setId(String id) { mId = id; }

    @NonNull
    public String getImagePath() {return mImagePath;}
}
