package com.example.recyclerviewtest.screen.roomDetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.content.Thing;
import com.example.recyclerviewtest.screen.roomlist.RoomsAdapter;

import java.util.List;

public class RoomDetailsAdapter extends RecyclerView.Adapter<RoomDetailsHolder> {


    private final List<Thing> mThings;

    private final OnItemChange mOnItemChange;

    private final CompoundButton.OnCheckedChangeListener mOnItemChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {



            Thing thing = (Thing) compoundButton.getTag();
            mOnItemChange.onItemChange(thing);
        }
    };


    public RoomDetailsAdapter(@NonNull List<Thing> things, @NonNull OnItemChange onItemChange) {

        mThings = things;
        mOnItemChange = onItemChange;
    }


    public void changeDataSet(@NonNull List<Thing> things) {

        mThings.clear();
        mThings.addAll(things);
        notifyDataSetChanged();
    }


    @Override
    public RoomDetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.item_room_detail, parent, false);

        return new RoomDetailsHolder(item);
    }

    @Override
    public void onBindViewHolder(RoomDetailsHolder holder, int position) {

        Thing thing = mThings.get(position);

        holder.bind(thing, mOnItemChangeListener);
        holder.mSwitch.setTag(thing);
    }

    @Override
    public int getItemCount() {
        return mThings.size();
    }


    public interface OnItemChange {
        void onItemChange(@NonNull Thing thing);
    }
}
