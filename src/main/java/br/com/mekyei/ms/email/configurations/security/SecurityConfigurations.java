package br.com.mekyei.ms.email.configurations.security;

import br.com.mekyei.ms.email.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe para configurações de segurança da API.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    TokenService tokenService;

    @Autowired
    ClienteRepository clienteRepository;

    /**
     * Cria o AuthenticationManager através do método da classe herdada.
     */
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * Configurações de autenticação: realiza a autenticação dos clientes.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* Realiza autenticação do cliente através da lógica contida no service e criptografa a senha. */
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Configurações de autorização: controla o acesso dos endpoints assim como os perfis de acesso.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth").permitAll() /* Requisicões para o endpoint não precisa de autenticação. */
                .antMatchers(HttpMethod.GET, "/actuator/**").authenticated() /* Requisicões para o endpoint precisa de autenticação. */
                .anyRequest().authenticated() /* Todas as demais requisições precisam de autenticação */
                .and().csrf().disable() /* Desabilita o CSRF */
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) /* Define que a autenticação será realizada a cada requisição, via token e não por sessão. */
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, clienteRepository), UsernamePasswordAuthenticationFilter.class); /* Adiciona o filtro de validação do token antes do filtro padrão. */
    }

    /**
     * Configurações de recursos estáticos: requisições para arquivos js, css, imagens, etc.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

    }
}
