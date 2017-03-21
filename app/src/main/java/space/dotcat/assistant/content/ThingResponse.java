package space.dotcat.assistant.content;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ThingResponse {

    @SerializedName("things")
    private List<Thing> mThings = new ArrayList<>();

    private ThingResponse(@NonNull List<Thing> things) {
        mThings = things;
    }

    @NonNull
    public List<Thing> getThings() { return mThings; }
}
