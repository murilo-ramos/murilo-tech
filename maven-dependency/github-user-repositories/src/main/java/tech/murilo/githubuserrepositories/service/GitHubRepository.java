package tech.murilo.githubuserrepositories.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubRepository {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "forks_count")
    private Integer forksCount;

    @JsonProperty(value = "stargazers_count")
    private Integer starsCount;

    public GitHubRepository() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getForksCount() {
        return forksCount;
    }

    public void setForksCount(Integer forksCount) {
        this.forksCount = forksCount;
    }

    public Integer getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(Integer starsCount) {
        this.starsCount = starsCount;
    }
}
