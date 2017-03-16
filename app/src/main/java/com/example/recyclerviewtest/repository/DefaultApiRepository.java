package com.example.recyclerviewtest.repository;


import android.support.annotation.NonNull;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

import com.example.recyclerviewtest.content.Message;
import com.example.recyclerviewtest.content.RoomResponse;
import com.example.recyclerviewtest.content.Thing;
import com.example.recyclerviewtest.content.ThingResponse;
import com.example.recyclerviewtest.utils.RxUtils;
import com.example.recyclerviewtest.api.ApiFactory;
import com.example.recyclerviewtest.content.Room;

import java.util.List;

import rx.Observable;


public class DefaultApiRepository implements ApiRepository {

    @NonNull
    @Override
    public Observable<List<Room>> rooms() {
        return ApiFactory.getApiService()
                .rooms()
                .map(RoomResponse::getRooms)
                .flatMap(rooms -> {
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        realm.delete(Room.class);
                        realm.insert(rooms);
                    });
                    return Observable.just(rooms);
                })
                .onErrorResumeNext(throwable -> {

                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<Room> rooms = realm.where(Room.class).findAll();

                    return Observable.just(realm.copyFromRealm(rooms));
                })
                .compose(RxUtils.async());
    }

    @NonNull
    @Override
    public Observable<List<Thing>> things(String id) {
        return ApiFactory.getApiService()
                .things(id)
                .map(ThingResponse::getThings)
                .flatMap(things -> {
                    Realm.getDefaultInstance().executeTransaction(realm ->{
                        realm.insertOrUpdate(things);
                    });
                    return Observable.just(things);
                })
                .onErrorResumeNext(throwable -> {

                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<Thing> things = realm.where(Thing.class).equalTo("mPlacement", id).findAll();

                    return Observable.just(realm.copyFromRealm(things));
                })
                .compose(RxUtils.async());
    }

    @NonNull
    @Override
    public Observable<Message> action(@NonNull Message message) {
        return ApiFactory.getApiService()
                .action(message)
                .compose(RxUtils.async());
    }


}
