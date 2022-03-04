package io.getarrays.userservice.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Caio Lucas (https://github.com/caiolucass)
 * @version 1.0
 * @since 02/03/2022
 *
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @ApiModelProperty(value = "Código do usuario")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Nome do usuario")
    private String name;

    @ApiModelProperty(value = "Nome do usuario")
    private String username;

    @ApiModelProperty(value = "Senha do usuario")
    private String password;

    @ApiModelProperty(value = "Cargos de niveis de acesso do usuario")
    //quando carregar o usuario, trazer toda sua lista de Role
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}
