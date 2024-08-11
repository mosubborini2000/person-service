package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.UserRequest;
import org.acme.dto.UserResponse;
import org.acme.service.UserService;

import java.util.List;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;




    @POST
    @Path("/create")
    public UserResponse createUser(UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GET
    @Path("/{id}")
    public UserResponse getUserById(@PathParam("id") Long id) {
        return userService.getUserById(id);
    }

    @GET
    @Path("/all")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}

