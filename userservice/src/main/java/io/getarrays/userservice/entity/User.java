package io.getarrays.userservice.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Caio Lucas (https://github.com/caiolucass)
 * @version 1.0
 * @since 02/03/2022
 *
 */

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
