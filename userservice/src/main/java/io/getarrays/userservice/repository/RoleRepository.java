package io.getarrays.userservice.repository;

import io.getarrays.userservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Caio Lucas (https://github.com/caiolucass)
 * @version 1.0
 * @since 02/03/2022
 *
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
