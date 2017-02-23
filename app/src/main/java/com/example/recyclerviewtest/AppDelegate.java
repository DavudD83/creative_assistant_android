package com.example.recyclerviewtest;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.recyclerviewtest.repository.RepositoryProvider;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;
import io.realm.rx.RealmObservableFactory;


public class AppDelegate extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration configuration = new RealmConfiguration.Builder(this)
                .rxFactory(new RealmObservableFactory())
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
