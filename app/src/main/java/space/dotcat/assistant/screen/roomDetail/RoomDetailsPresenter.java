package space.dotcat.assistant.screen.roomDetail;

import android.support.annotation.NonNull;

import space.dotcat.assistant.R;
import space.dotcat.assistant.content.ActionParams;
import space.dotcat.assistant.content.Body;
import space.dotcat.assistant.content.Message;
import space.dotcat.assistant.content.Thing;
import space.dotcat.assistant.repository.RepositoryProvider;

import ru.arturvasilov.rxloader.LifecycleHandler;

public class RoomDetailsPresenter {

    private final LifecycleHandler mLifecycleHandler;

    private final RoomDetailsView mRoomDetailsView;

    RoomDetailsPresenter(@NonNull LifecycleHandler lifecycleHandler,
                         @NonNull RoomDetailsView roomDetailsView) {
        mLifecycleHandler = lifecycleHandler;
        mRoomDetailsView = roomDetailsView;
    }

    public void init(@NonNull String id) {
        RepositoryProvider.provideApiRepository()
                .things(id)
                .doOnSubscribe(mRoomDetailsView::showLoading)
                .doOnTerminate(mRoomDetailsView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.thing_request))
                .subscribe(mRoomDetailsView::showThings, throwable -> mRoomDetailsView.showError());
    }

    public void reloadData(@NonNull String id) {
        RepositoryProvider.provideApiRepository()
                .things(id)
                .compose(mLifecycleHandler.reload(R.id.thing_request))
                .subscribe(mRoomDetailsView::showThings, throwable -> mRoomDetailsView.showError());
    }

    public void onItemChange(@NonNull Thing thing) {

        ActionParams actionParams = new ActionParams();
        Body body = new Body("toggle", thing.getId(), actionParams);
        Message message = new Message(body);

        RepositoryProvider.provideApiRepository()
                .action(message)
                .doOnSubscribe(mRoomDetailsView::showLoading)
                .doOnTerminate(mRoomDetailsView::hideLoading)
                .compose(mLifecycleHandler.reload(R.id.action_request))
                .subscribe(message1 -> {},
                        throwable -> mRoomDetailsView.showError());
    }


}
