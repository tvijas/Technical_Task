package org.acme.models;

import java.util.List;

public record GithubRepo(
        String name,
        String owner,
        List<GithubBranch> githubBranches
) {
}
