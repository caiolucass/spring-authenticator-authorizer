## USER SERVICE API

API de estudo feita em SPRING afim de estudar e aprender sobre o spring security.

### Dependencias

* Spring Security 
* Lombok
* MYSQL
* Spring Dev Tool
* Spring Starter Web
* Spring Data JPA
* Auth0

## Como utilizar?

* Para usar a API e necessario a criacao de um perfil de usuario.
* E efetuar o Login
* Para determinadas funcoes da API (Delete, Insert, Post, Update) e preciso criar/vincular um cargo ao perfil do usuario.

## Autenticacao, autorizacao e cripitografia

* A criptografia e feita utilzando BCryptPasswordEncoder, Algorithm.HMAC256
* A autenticacao e feita utilizando WebSecurityConfigurerAdapter, AuthenticationManager, CustonAuthenticationFilter, attemptAuthentication, successfulAuthentication
* A autorizacao e feita utilizando SimpleGrantedAuthority, WebSecurityConfigurerAdapter
