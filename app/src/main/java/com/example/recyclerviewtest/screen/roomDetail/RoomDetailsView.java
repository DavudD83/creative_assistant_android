package com.example.recyclerviewtest.screen.roomDetail;

import android.support.annotation.NonNull;

import com.example.recyclerviewtest.content.Thing;
import com.example.recyclerviewtest.screen.general.LoadingView;

import java.util.List;

public interface RoomDetailsView extends LoadingView {

    void showThings(@NonNull List<Thing> things);

    void showError();
}
