package com.test.app.LoadMaps;

import android.app.Application;

import com.test.app.LoadMaps.model.MVPModelImplementor;
import com.test.app.LoadMaps.model.db.RealmDBAdapter;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    static RealmDBAdapter realmDBAdapterInstance;
    static MVPModelImplementor model;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                // data to database on ui thread.
                .allowWritesOnUiThread(true) // below line is to delete realm
                // if migration is needed.
                .deleteRealmIfMigrationNeeded() // at last we are calling a method to build.
                .build();
        Realm.setDefaultConfiguration(config);
        realmDBAdapterInstance = RealmDBAdapter.getInstance(this);
        model = new MVPModelImplementor(realmDBAdapterInstance);
    }

    public static RealmDBAdapter getRealmDBAdapterInstance() {
        return realmDBAdapterInstance;
    }
    public static MVPModelImplementor getModel(){
        return model;
    }

}
