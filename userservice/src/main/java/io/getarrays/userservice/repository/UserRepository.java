package io.getarrays.userservice.repository;

import io.getarrays.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Caio Lucas (https://github.com/caiolucass)
 * @version 1.0
 * @since 02/03/2022
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
