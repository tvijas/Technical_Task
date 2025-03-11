package org.acme.services;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.api.client.github.GitHubApiClient;
import org.acme.api.client.github.models.GitHubRepo;
import org.acme.models.GithubBranch;
import org.acme.models.GithubRepo;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GithubRepoService {
    @Inject
    @RestClient
    GitHubApiClient gitHubApiClient;

    public Uni<List<GithubRepo>> getUserRepositories(String username) {
        return gitHubApiClient.getUserRepositories(username)
                .onItem().transform(repos -> repos.stream()
                        .filter(repo -> !repo.fork())
                        .collect(Collectors.toList()))
                .onItem().transformToUni(nonForkRepos -> {
                    List<Uni<GithubRepo>> githubRepo = nonForkRepos.stream()
                            .map(this::fetchRepoWithBranches)
                            .collect(Collectors.toList());

                    return Uni.join().all(githubRepo).andCollectFailures();
                });
    }

    private Uni<GithubRepo> fetchRepoWithBranches(GitHubRepo repo) {
        return gitHubApiClient.getRepositoryBranches(repo.owner().login(), repo.name())
                .map(branches -> {
                    List<GithubBranch> branchList = branches.stream()
                            .map(branch -> new GithubBranch(branch.name(), branch.commit().sha()))
                            .collect(Collectors.toList());

                    return new GithubRepo(repo.name(), repo.owner().login(), branchList);
                });
    }
}
