package io.getarrays.userservice.controller;


import io.getarrays.userservice.model.Role;
import io.getarrays.userservice.model.User;
import io.getarrays.userservice.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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

    @ApiOperation(value = "Retorna uma lista de usuarios")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de usuarios"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Servico nao encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor", response = User.class, responseContainer = "List")
    })
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces="application/json", consumes="application/json")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @ApiOperation(value = "Retorna o usuario cadastrado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna o usuario cadastrado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Servico nao encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor", response = User.class, responseContainer = "User")
    })
    @RequestMapping(value = "/user/save", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @ApiOperation(value = "Retorna o cargo cadastrado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna o cargo cadastrado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Servico nao encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor", response = Role.class, responseContainer = "Role")
    })
    @RequestMapping(value = "/role/save", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @ApiOperation(value = "Retorna o cargo vinculado ao usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna o vinculado usuario"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Servico nao encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor", response = Role.class, responseContainer = "Role")
    })
    @RequestMapping(value = "/role/addtouser", method = RequestMethod.POST, produces="application/json", consumes="application/json")
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
