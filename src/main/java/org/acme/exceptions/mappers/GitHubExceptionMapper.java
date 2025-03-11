package org.acme.exceptions.mappers;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.exceptions.responses.BasicExceptionResponse;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

@Provider
public class GitHubExceptionMapper implements ExceptionMapper<ClientWebApplicationException> {

    @Override
    public Response toResponse(ClientWebApplicationException ex) {
        BasicExceptionResponse response = new BasicExceptionResponse(
                Response.Status.NOT_FOUND.getStatusCode(),
                "Github with such user not found"
        );
        return Response.status(Response.Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .entity(response)
                .build();
    }
}
