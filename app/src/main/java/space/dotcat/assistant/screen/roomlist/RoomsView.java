package space.dotcat.assistant.screen.roomlist;

import android.support.annotation.NonNull;

import space.dotcat.assistant.content.Room;
import space.dotcat.assistant.screen.general.LoadingView;

import java.util.List;

public interface RoomsView extends LoadingView {

     void showRooms(@NonNull List<Room> rooms);

     void showRoomDetail(@NonNull Room room);

     void showError();

}
