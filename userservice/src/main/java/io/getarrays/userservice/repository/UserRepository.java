package io.getarrays.userservice.repository;

import io.getarrays.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Caio Lucas (https://github.com/caiolucass)
 * @version 1.0
 * @since 02/03/2022
 *
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
