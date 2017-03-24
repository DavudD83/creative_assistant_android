package space.dotcat.assistant.screen.roomDetail;

import android.support.annotation.NonNull;

import space.dotcat.assistant.content.Thing;
import space.dotcat.assistant.screen.general.LoadingView;

import java.util.List;

public interface RoomDetailsView extends LoadingView {

    void showThings(@NonNull List<Thing> things);

    void showError();
}
