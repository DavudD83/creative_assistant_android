package com.example.CreativeAssistant.screen.roomDetail;

import android.support.annotation.NonNull;

import com.example.CreativeAssistant.content.Thing;
import com.example.CreativeAssistant.screen.general.LoadingView;

import java.util.List;

public interface RoomDetailsView extends LoadingView {

    void showThings(@NonNull List<Thing> things);

    void showError();
}
