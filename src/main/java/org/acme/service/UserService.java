package org.acme.service;

import org.acme.dto.UserRequest;
import org.acme.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequestDTO);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
}
