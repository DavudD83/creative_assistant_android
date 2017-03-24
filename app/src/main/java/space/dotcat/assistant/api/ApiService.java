package space.dotcat.assistant.api;

import space.dotcat.assistant.content.Message;
import space.dotcat.assistant.content.RoomResponse;
import space.dotcat.assistant.content.ThingResponse;

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
