package com.example.recyclerviewtest.api;

import com.example.recyclerviewtest.content.Message;
import com.example.recyclerviewtest.content.Room;
import com.example.recyclerviewtest.content.RoomResponse;
import com.example.recyclerviewtest.content.ThingResponse;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

import retrofit2.http.GET;

public interface ApiService {

    @GET("placements/")
    Observable<RoomResponse> rooms();

    @GET("things/")
    Observable<ThingResponse> things(@Query("placement") String id);

    @POST("messages/")
    Observable<Message> action(@Body Message message);
}
