package org.acme.api.client.github;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.api.client.github.models.GitHubRepo;
import org.acme.api.client.github.models.GithubBranch;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;
@RegisterRestClient(configKey = "github-api")
public interface GitHubApiClient {

    @GET
    @Path("/users/{username}/repos")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<GitHubRepo>> getUserRepositories(@PathParam("username") String username);

    @GET
    @Path("/repos/{owner}/{repo}/branches")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<GithubBranch>> getRepositoryBranches(@PathParam("owner") String owner, @PathParam("repo") String repo);
}
