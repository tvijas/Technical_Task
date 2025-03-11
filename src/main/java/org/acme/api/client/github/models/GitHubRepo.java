package org.acme.api.client.github.models;

public record GitHubRepo(String name, Owner owner, boolean fork) {
}
