package com.example.CreativeAssistant.screen.roomlist;

import android.support.annotation.NonNull;

import com.example.CreativeAssistant.content.Room;
import com.example.CreativeAssistant.screen.general.LoadingView;

import java.util.List;

public interface RoomsView extends LoadingView {

     void showRooms(@NonNull List<Room> rooms);

     void showRoomDetail(@NonNull Room room);

     void showError();

}
