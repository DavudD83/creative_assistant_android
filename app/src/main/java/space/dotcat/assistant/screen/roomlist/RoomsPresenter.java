package space.dotcat.assistant.screen.roomlist;


import android.support.annotation.NonNull;

import space.dotcat.assistant.R;
import space.dotcat.assistant.content.Room;
import space.dotcat.assistant.repository.RepositoryProvider;

import ru.arturvasilov.rxloader.LifecycleHandler;

public class RoomsPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final RoomsView mRoomsView;

    public RoomsPresenter(@NonNull LifecycleHandler lifecycleHandler,
                          @NonNull RoomsView roomsView) {
        mLifecycleHandler = lifecycleHandler;
        mRoomsView = roomsView;
    }

    public void init() {
        RepositoryProvider.provideApiRepository()
                .rooms()
                .doOnSubscribe(mRoomsView::showLoading)
                .doOnTerminate(mRoomsView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.room_request))
                .subscribe(mRoomsView::showRooms, throwable -> mRoomsView.showError());
    }

    public void reloadData() {
        RepositoryProvider.provideApiRepository()
                .rooms()
                .compose(mLifecycleHandler.reload(R.id.reload_rooms))
                .subscribe(mRoomsView::showRooms, throwable -> mRoomsView.showError());
    }

    public void onItemClick(@NonNull Room room) {
        mRoomsView.showRoomDetail(room);
    }

}
