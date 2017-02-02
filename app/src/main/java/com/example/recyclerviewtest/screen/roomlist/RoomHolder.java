package com.example.recyclerviewtest.screen.roomlist;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerviewtest.AppDelegate;
import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.content.Room;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.roomName)
    TextView mRoomName;

    @BindView(R.id.roomPicture)
    ImageView mRoomPicture;


    public RoomHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(@NonNull Room room) {

        mRoomName.setText(room.GetDescription());

        Picasso.with(AppDelegate.getContext())
                .load(room.getImagePath())
                .noFade()
                .into(mRoomPicture);
    }
}
