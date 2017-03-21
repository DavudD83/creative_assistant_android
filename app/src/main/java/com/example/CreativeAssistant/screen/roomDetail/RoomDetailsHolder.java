package com.example.CreativeAssistant.screen.roomDetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;

import com.example.CreativeAssistant.R;
import com.example.CreativeAssistant.content.Thing;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomDetailsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.switchThing)
    Switch mSwitch;

    public RoomDetailsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(@NonNull Thing thing) {

        mSwitch.setText(thing.getId());
        mSwitch.setEnabled(thing.getIsAvailable());
        mSwitch.setChecked(thing.getIsActive());
    }

}
