package tech.murilo.githubuserrepositories.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import tech.murilo.githubuserrepositories.service.GitHubRepository;

import java.util.List;

public interface GitHubRepositoriesClient {

    @GET("users/{user}/repos")
    Call<List<GitHubRepository>> listRepositoriesFromUser(@Path("user") String user);

}
