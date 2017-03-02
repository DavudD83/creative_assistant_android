package com.example.recyclerviewtest.repository;


import android.support.annotation.NonNull;

import com.example.recyclerviewtest.content.Message;
import com.example.recyclerviewtest.content.Room;
import com.example.recyclerviewtest.content.Thing;

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
