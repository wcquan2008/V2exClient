package com.github.wcquan.library.db;

import javax.inject.Inject;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by WCQUAN on 2017-06-01.
 */

public class RealmHelper implements IDBHelper {

    private static final String DB_NAME = "v2exclient.realm";

    private Realm mRealm;

    @Inject
    public RealmHelper() {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }

}
