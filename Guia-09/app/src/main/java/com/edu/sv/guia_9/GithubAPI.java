package com.edu.sv.guia_9;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubAPI {

    @GET("/users/{user}/repos")
    Call<List<GithubRepo>> repoForUser(@Path("user") String user);
}
