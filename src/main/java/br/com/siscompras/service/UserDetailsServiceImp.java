package br.com.siscompras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    /*
    * Tem platicamente a mesma funcionalidade do AuthenticationProvieder
    * O UserDetails vai pegar o usuario e fazer a authenticação no sistema.
    * Se não haver usuario especificado, o mesmo não vai liberar o acesso ao sistema
    * */

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return User.builder()
                .username("123")
                .password(passwordEncoder.encode("123"))
                .roles("ADMINISTRADOR")
                .build();
    }
}
