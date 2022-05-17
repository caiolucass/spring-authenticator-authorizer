package io.getarrays.userservice.controller;


import io.getarrays.userservice.entity.Role;
import io.getarrays.userservice.entity.User;
import io.getarrays.userservice.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author Caio Lucas (https://github.com/caiolucass)
 * @version 1.0
 * @since 02/03/2022
 *
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@RequestBody String username){
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    @RequestMapping(value = "/user/saveUser", method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @RequestMapping(value = "/role/saveRole", method = RequestMethod.POST)
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @RequestMapping(value = "/role/addtouser", method = RequestMethod.POST)
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm) {
        userService.addRoleToUser(roleToUserForm.getUserName(), roleToUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }
}

@Data
class RoleToUserForm {
    private String userName;
    private String roleName;
}
