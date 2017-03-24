package space.dotcat.assistant.screen.roomlist;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import space.dotcat.assistant.R;
import space.dotcat.assistant.content.Room;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text123)
    TextView mRoomName;

    @BindView(R.id.Image)
    ImageView mRoomPicture;


    public RoomHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(@NonNull Room room) {

        mRoomName.setText(room.GetDescription());

        Picasso.with(mRoomPicture.getContext())
                .load(room.getImagePath())
                .resize(200,160)
                .noFade()
                .into(mRoomPicture);
    }
}
