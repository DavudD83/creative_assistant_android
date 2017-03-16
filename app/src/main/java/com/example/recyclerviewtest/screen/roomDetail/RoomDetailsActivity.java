package com.example.recyclerviewtest.screen.roomDetail;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.content.Room;
import com.example.recyclerviewtest.content.Thing;
import com.example.recyclerviewtest.screen.general.LoadingDialog;
import com.example.recyclerviewtest.screen.general.LoadingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class RoomDetailsActivity extends AppCompatActivity implements RoomDetailsView, RoomDetailsAdapter.OnItemChange,
        SwipeRefreshLayout.OnRefreshListener {

    private final static String EXTRA_ROOM = "room";

    private Room mRoom;

    private LoadingView mLoadingView;

    private RoomDetailsAdapter mRoomDetailsAdapter;

    private RoomDetailsPresenter mRoomDetailsPresenter;

    @BindView(R.id.swipeRefreshDetails)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recyclerViewDetails)
    RecyclerView mRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static void start(@NonNull Activity activity, @NonNull Room room) {
        Intent intent = new Intent(activity, RoomDetailsActivity.class);
        intent.putExtra(EXTRA_ROOM, room);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light);

        mRoom = (Room) getIntent().getSerializableExtra(EXTRA_ROOM);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(mRoom.GetDescription());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        mRoomDetailsAdapter = new RoomDetailsAdapter(new ArrayList<>(), this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRoomDetailsAdapter);

        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        mRoomDetailsPresenter = new RoomDetailsPresenter(lifecycleHandler, this);
        mRoomDetailsPresenter.init(mRoom.GetId());
    }

    @Override
    public void showThings(@NonNull List<Thing> things) {
        mRoomDetailsAdapter.changeDataSet(things);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Some problem", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    public void onItemChange(@NonNull Thing thing) {
        mRoomDetailsPresenter.onItemChange(thing);
    }

    @Override
    public void onRefresh() {

        mRoomDetailsPresenter.reloadData(mRoom.GetId());
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
