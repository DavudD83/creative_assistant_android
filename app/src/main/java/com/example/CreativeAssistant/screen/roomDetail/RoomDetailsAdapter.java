package com.example.CreativeAssistant.screen.roomDetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.CreativeAssistant.R;
import com.example.CreativeAssistant.content.Thing;

import java.util.List;

public class RoomDetailsAdapter extends RecyclerView.Adapter<RoomDetailsHolder> {


    private final List<Thing> mThings;

    private final OnItemChange mOnItemChange;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Thing thing = (Thing) view.getTag();
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

        holder.bind(thing);
        holder.mSwitch.setTag(thing);
        holder.mSwitch.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mThings.size();
    }


    public interface OnItemChange {
        void onItemChange(@NonNull Thing thing);
    }
}
