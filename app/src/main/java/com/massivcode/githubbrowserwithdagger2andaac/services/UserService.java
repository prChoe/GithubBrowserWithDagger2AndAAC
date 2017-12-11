package com.massivcode.githubbrowserwithdagger2andaac.services;

import com.massivcode.githubbrowserwithdagger2andaac.models.BaseUserResponse;
import com.massivcode.githubbrowserwithdagger2andaac.models.GistResponse;
import com.massivcode.githubbrowserwithdagger2andaac.models.RepositoryResponse;
import com.massivcode.githubbrowserwithdagger2andaac.models.UserResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by massivcode@gmail.com on 2017-12-11.
 */

public interface UserService {
  @GET("users/{loginName}")
  Call<UserResponse> fetchUser(@Path("loginName") String userLoginName);

  @GET("users/{loginName}/followers")
  Call<List<BaseUserResponse>> fetchFollowers(@Path("loginName") String userLoginName);

  @GET("users/{loginName}/repos")
  Call<List<RepositoryResponse>> fetchRepositories(@Path("loginName") String userLoginName);

  @GET("users/{loginName}/followers")
  Call<List<BaseUserResponse>> fetchFollowing(@Path("loginName") String userLoginName);

  @GET("users/{loginName}/gists")
  Call<List<GistResponse>> fetchGists(@Path("loginName") String userLoginName);
}
