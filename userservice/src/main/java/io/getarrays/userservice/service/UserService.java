package io.getarrays.userservice.service;

import io.getarrays.userservice.model.Role;
import io.getarrays.userservice.model.User;

import java.util.List;

/**
 * @author Caio Lucas (https://github.com/caiolucass)
 * @version 1.0
 * @since 02/03/2022
 *
 */
public interface UserService {
    User saveUser(User save);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
