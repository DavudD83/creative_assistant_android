package space.dotcat.assistant.screen.roomDetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import space.dotcat.assistant.R;
import space.dotcat.assistant.content.Room;
import space.dotcat.assistant.content.Thing;
import space.dotcat.assistant.screen.general.LoadingDialog;
import space.dotcat.assistant.screen.general.LoadingView;

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

    @BindView(R.id.tv_error_message)
    TextView mErrorMessage;

    private final View.OnClickListener mSnackBarButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showLoading();
            mRoomDetailsPresenter.reloadData(mRoom.GetId());
            hideLoading();
        }
    };

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

        if (!things.isEmpty()) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mErrorMessage.setVisibility(View.INVISIBLE);
            mRoomDetailsAdapter.changeDataSet(things);
        } else {
            mRecyclerView.setVisibility(View.INVISIBLE);
            mErrorMessage.setVisibility(View.VISIBLE);
            mErrorMessage.setText(getString(R.string.error_empty_actions));
        }
    }

    @Override
    public void showError() {
        Snackbar snackbar = Snackbar.make(mSwipeRefreshLayout, R.string.network_problem,
                Snackbar.LENGTH_INDEFINITE);

        snackbar.setAction(getString(R.string.refresh), mSnackBarButtonListener);
        snackbar.show();
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
