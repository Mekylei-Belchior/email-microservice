package br.com.mekyei.ms.email.configurations.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Classe para configurações de segurança da API.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    /**
     * Configurações de autenticação: determina o controle de acesso das URL's (endpoints) da API.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }

    /**
     * Configurações de autorização: controla o acesso através de perfis de acesso
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/envia-email").authenticated()
                .anyRequest().authenticated()
                .and().csrf().disable();

    }

    /**
     * Configurações de recursos estáticos: requisições para arquivos js, css, imagens, etc.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

    }
}
