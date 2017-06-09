package com.github.wcquan.library.model.db;

import javax.inject.Inject;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by WCQUAN on 2017-06-01.
 */

public class RealmHelper implements IDBHelper {

    private Realm mRealm;

    public RealmHelper(Realm realm) {
        this.mRealm = realm;
    }

}
