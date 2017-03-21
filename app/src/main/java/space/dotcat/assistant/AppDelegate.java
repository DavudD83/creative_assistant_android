package space.dotcat.assistant;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
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
