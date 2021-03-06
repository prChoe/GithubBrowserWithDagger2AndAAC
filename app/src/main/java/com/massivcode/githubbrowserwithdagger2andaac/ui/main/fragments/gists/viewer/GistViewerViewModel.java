package com.massivcode.githubbrowserwithdagger2andaac.ui.main.fragments.gists.viewer;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.massivcode.githubbrowserwithdagger2andaac.models.local.Gist;
import com.massivcode.githubbrowserwithdagger2andaac.models.local.User;
import com.massivcode.githubbrowserwithdagger2andaac.models.local.daos.GistDao;
import com.massivcode.githubbrowserwithdagger2andaac.models.local.daos.SearchSuggestionDao;
import com.massivcode.githubbrowserwithdagger2andaac.models.local.daos.UserDao;
import com.massivcode.githubbrowserwithdagger2andaac.repositories.GistRepository;
import com.massivcode.githubbrowserwithdagger2andaac.repositories.Resource;
import com.massivcode.githubbrowserwithdagger2andaac.repositories.UserRepository;
import com.massivcode.githubbrowserwithdagger2andaac.utils.network.NetworkModule;
import io.realm.Realm;

/**
 * Created by massivcode@gmail.com on 2017-12-19.
 */

public class GistViewerViewModel extends ViewModel {

  private Realm mRealm;
  private UserRepository mUserRepository;
  private GistRepository mGistRepository;

  public GistViewerViewModel() {
    mRealm = Realm.getDefaultInstance();

    NetworkModule networkModule = NetworkModule.getInstance();
    mUserRepository = new UserRepository(networkModule.provideNetworkUtil(),
        networkModule.provideUserService(), new SearchSuggestionDao(mRealm), new UserDao(mRealm));
    mGistRepository = new GistRepository(new GistDao(mRealm), new UserDao(mRealm),
        networkModule.provideGistService(), networkModule.provideUserService());
  }

  public LiveData<Resource<User>> getUserLiveData(String loginName) {
    return mUserRepository.getUser(loginName);
  }

  public LiveData<Resource<Gist>> getGistLiveData(User user, String gistId) {
    return mGistRepository.getGist(user, gistId);
  }

  @Override
  protected void onCleared() {
    mRealm.close();
    super.onCleared();
  }
}
