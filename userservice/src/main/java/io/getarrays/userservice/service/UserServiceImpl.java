package io.getarrays.userservice.service;

import io.getarrays.userservice.model.Role;
import io.getarrays.userservice.model.User;
import io.getarrays.userservice.repository.RoleRepository;
import io.getarrays.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Caio Lucas (https://github.com/caiolucass)
 * @version 1.0
 * @since 02/03/2022
 *
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService , UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     * Verifica se o usuario esta cadastrado no banco de dados
     * caso estaja, ira verificar quais permissoes o usuario possui
     * e adiciona uma nova permissao caso seja preciso
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.error("Usuario nao encontrada no banco de dados");
            throw  new UsernameNotFoundException("Usuario nao encontrada no banco de dados");
        }else{
            log.info("Usuario encontrada no banco de dados:{}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public User saveUser(User user) {
        log.info("Salvando novo usuario {} no banco de dados.", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     *
     * @param role
     * @return
     */
    @Override
    public Role saveRole(Role role) {
        log.info("Salvando nova role {} no banco de dados.", role.getName());
        return roleRepository.save(role);
    }

    /**
     *
     * @param username
     * @param roleName
     */
    @Override
    public void addRoleToUser(String username, String roleName) {
      log.info("Adicionando nova role {} para o usuario {} no banco de dados.", roleName, username);
      User user = userRepository.findByUsername(username);
      Role role = roleRepository.findByName(roleName);
      user.getRoles().add(role);
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public User getUser(String username) {
        log.info("Fetching usuario {}", username);
        return userRepository.findByUsername(username);
    }

    /**
     *
     * @return
     */
    @Override
    public List<User> getUsers() {
        log.info("Fetching todos usuarios.");
        return userRepository.findAll();
    }
}
