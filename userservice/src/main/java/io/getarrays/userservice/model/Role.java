package io.getarrays.userservice.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Role {

    @ApiModelProperty(value = "Código do cargo de nivel de acesso do usuario")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Nome do cargo de nivel de acesso do usuario")
    private String name;
}
