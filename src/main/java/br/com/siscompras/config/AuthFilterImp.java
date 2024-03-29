package br.com.siscompras.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class AuthFilterImp extends OncePerRequestFilter {


    /*
     * O security filterChain vai ser iniciado para poder ser feito a primeira filtragem do usuario capturando o token que
     * estara no header e com isso fazer todas as validações
     * e depois colocar no contexto do Spring Security com objetivo de continuar com as demais autenticações
     * */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String get = request.getHeader("auth");
        if (get.equals("123")){
            Authentication a = new UsernamePasswordAuthenticationToken("Eduardo", "123", List.of(new SimpleGrantedAuthority("ADMINISTRADOR")));
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(a);
        }

        filterChain.doFilter(request, response);
    }
}
