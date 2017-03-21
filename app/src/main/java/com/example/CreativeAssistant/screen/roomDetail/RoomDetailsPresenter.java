package com.example.CreativeAssistant.screen.roomDetail;

import android.support.annotation.NonNull;

import com.example.CreativeAssistant.R;
import com.example.CreativeAssistant.content.ActionParams;
import com.example.CreativeAssistant.content.Body;
import com.example.CreativeAssistant.content.Message;
import com.example.CreativeAssistant.content.Thing;
import com.example.CreativeAssistant.repository.RepositoryProvider;

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
                .compose(mLifecycleHandler.reload(R.id.reload_things))
                .subscribe(mRoomDetailsView::showThings, throwable -> mRoomDetailsView.showError());
    }

    public void onItemChange(@NonNull Thing thing) {

        ActionParams actionParams = new ActionParams();
        Body body = new Body("toggle", thing.getId(), actionParams);
        Message message = new Message(body);

        RepositoryProvider.provideApiRepository()
                .action(message)
                .compose(mLifecycleHandler.reload(R.id.action_request))
                .subscribe(message1 -> {},
                        throwable -> mRoomDetailsView.showError());
    }


}
