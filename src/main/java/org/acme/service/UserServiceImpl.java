package org.acme.service;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.dto.UserRequest;
import org.acme.dto.UserResponse;
import org.acme.model.User;
import org.acme.repository.UserRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    @RestClient
    DepartmentServiceClient departmentServiceClient;
    @Transactional
    @Override
    public UserResponse createUser(UserRequest userRequestDTO) {
        User user = User.builder()
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .departmentId(userRequestDTO.getDepartmentId())
                .build();
        userRepository.persist(user);
        return mapToUserResponse(user);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id);
        return mapToUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.listAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    private UserResponse mapToUserResponse(User user) {
        String departmentName = departmentServiceClient.getDepartmentByIdClient(user.getDepartmentId()).getName();
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .departmentName(departmentName)
                .build();
    }
}
