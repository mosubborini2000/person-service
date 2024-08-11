package org.acme.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.acme.dto.Department;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/departments")
@RegisterRestClient(baseUri = "http://localhost:8081")
public interface DepartmentServiceClient {

    @GET
    @Path("/{id}")
    Department getDepartmentByIdClient(@PathParam("id") Long id);
}
