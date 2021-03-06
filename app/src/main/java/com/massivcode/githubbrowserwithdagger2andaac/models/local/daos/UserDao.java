package com.massivcode.githubbrowserwithdagger2andaac.models.local.daos;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.massivcode.githubbrowserwithdagger2andaac.models.local.Gist;
import com.massivcode.githubbrowserwithdagger2andaac.models.local.Repository;
import com.massivcode.githubbrowserwithdagger2andaac.models.local.User;
import com.massivcode.githubbrowserwithdagger2andaac.utils.log.DLogger;
import com.massivcode.githubbrowserwithdagger2andaac.utils.realm.BaseDao;
import io.realm.Realm;
import io.realm.RealmList;
import java.util.List;

/**
 * Created by massivcode@gmail.com on 2017-12-12.
 */

public class UserDao extends BaseDao<User> {

  public UserDao(Realm mRealm) {
    super(mRealm);
  }

  public void add(User user) {
    if (mRealm.where(User.class).equalTo("loginName", user.getLoginName()).findFirst() != null) {
      DLogger.i("already exists return");
      return;
    }

    mRealm.beginTransaction();
    mRealm.copyToRealm(user);
    mRealm.commitTransaction();
  }

  public LiveData<User> findUser(String loginName) {
    MutableLiveData<User> liveData = new MutableLiveData<>();
    liveData.setValue(mRealm.where(User.class).equalTo("loginName", loginName).findFirst());
    return liveData;
  }

  public void addRepositories(User user, RealmList<Repository> repositories) {
    mRealm.beginTransaction();
    List<Repository> managed = mRealm.copyToRealmOrUpdate(repositories);
    RealmList<Repository> managedList = new RealmList<>();
    managedList.addAll(managed);
    user.setRepositories(managedList);
    mRealm.commitTransaction();
  }

  public void addGists(User user, RealmList<Gist> gists) {
    mRealm.beginTransaction();
    List<Gist> managed = mRealm.copyToRealmOrUpdate(gists);
    RealmList<Gist> managedList = new RealmList<>();
    managedList.addAll(managed);
    user.setGists(managedList);
    mRealm.commitTransaction();
  }


}
