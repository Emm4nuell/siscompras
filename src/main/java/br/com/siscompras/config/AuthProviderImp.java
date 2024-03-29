package br.com.siscompras.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthProviderImp implements AuthenticationProvider {

    /*
    O authProvider vai capturar a senha enviada do .httpBasic ou do .formlogin e fazer todas a verificaçoes para acessar
    o sistema
     */

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        boolean is = true;

        if (!is){
            return new UsernamePasswordAuthenticationToken(
                    authentication.getPrincipal(),
                    authentication.getCredentials(),
                    List.of(new SimpleGrantedAuthority("ADMINISTRADOR")));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
