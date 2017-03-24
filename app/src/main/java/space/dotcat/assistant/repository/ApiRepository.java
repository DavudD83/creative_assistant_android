package space.dotcat.assistant.repository;


import android.support.annotation.NonNull;

import space.dotcat.assistant.content.Message;
import space.dotcat.assistant.content.Room;
import space.dotcat.assistant.content.Thing;

import java.util.List;

import rx.Observable;

public interface ApiRepository {

    @NonNull
    Observable<List<Room>> rooms();

    @NonNull
    Observable<List<Thing>> things(String id);

    @NonNull
    Observable<Message> action(@NonNull Message message);
}
