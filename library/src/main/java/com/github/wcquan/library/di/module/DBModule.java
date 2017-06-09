package com.github.wcquan.library.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.wcquan.library.BuildConfig;
import com.github.wcquan.library.app.Constants;
import com.github.wcquan.library.model.db.IDBHelper;
import com.github.wcquan.library.model.db.RealmHelper;
import com.github.wcquan.library.model.pref.IPreferencesHelper;
import com.github.wcquan.library.model.pref.PreferencesHelper;
import com.github.wcquan.library.util.LogUtil;
import com.orhanobut.logger.Logger;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

/**
 * Created by WCQUAN on 2017-06-08.
 */

@Module
public class DBModule {

    @Provides
    @Singleton
    Realm provideRealm(Context context) {
        Realm.init(context);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .migration(new RealmMigration() {
                    @Override
                    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
                        LogUtil.i(String.format("migration  Old version %d -> New version %d", oldVersion, newVersion));
                    }
                })
                .schemaVersion(BuildConfig.VERSION_CODE)
                .deleteRealmIfMigrationNeeded()
                .name(Constants.DB_FILE_NAME)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    IDBHelper provideDBHelper(Realm realm) {
        return new RealmHelper(realm);
    }
}
