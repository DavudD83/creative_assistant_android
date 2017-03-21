package com.example.CreativeAssistant.repository;


import android.support.annotation.NonNull;

import com.example.CreativeAssistant.content.Message;
import com.example.CreativeAssistant.content.Room;
import com.example.CreativeAssistant.content.Thing;

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
