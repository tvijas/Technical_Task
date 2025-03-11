package org.acme.resources;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.models.GithubRepo;
import org.acme.services.GithubRepoService;

import java.util.List;

@Path("/api/v1/github/repo")
public class GithubRepoResource {
    @Inject
    GithubRepoService githubRepoService;
    @GET
    @Path("/user/{owner}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<GithubRepo>> getRepos(@PathParam("owner") String owner) {
        return githubRepoService.getUserRepositories(owner);
    }
}
