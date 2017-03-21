package com.example.CreativeAssistant.api;

import com.example.CreativeAssistant.content.Message;
import com.example.CreativeAssistant.content.RoomResponse;
import com.example.CreativeAssistant.content.ThingResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
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
