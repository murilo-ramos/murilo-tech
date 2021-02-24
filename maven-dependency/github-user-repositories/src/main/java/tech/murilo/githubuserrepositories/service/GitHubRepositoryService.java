package tech.murilo.githubuserrepositories.service;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import tech.murilo.githubuserrepositories.client.GitHubRepositoriesClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GitHubRepositoryService {

    private static final String GITHUB_URL = "https://api.github.com/";

    private final GitHubRepositoriesClient gitHubRepositoriesClient;

    public GitHubRepositoryService() {
        var retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        gitHubRepositoriesClient = retrofit.create(GitHubRepositoriesClient.class);
    }

    public List<GitHubRepository> listRepositoriesFromUser(String user) {
        var result = new ArrayList<GitHubRepository>();

        try {
            var request = gitHubRepositoriesClient.listRepositoriesFromUser(user);
            var response = request.execute();

            if (response.isSuccessful()) {
                result.addAll(response.body());
            }
        } catch (IOException ex) {
            System.out.println("Erro ao realizar requisição: " + ex.getMessage());
        }

        return result;
    }
}
