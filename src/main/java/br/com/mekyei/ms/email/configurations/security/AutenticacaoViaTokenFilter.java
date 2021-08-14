package br.com.mekyei.ms.email.configurations.security;

import br.com.mekyei.ms.email.models.Cliente;
import br.com.mekyei.ms.email.repositories.ClienteRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Esta classe será responsável por interceptar as requisições do cliente e validar o token de autenticação.
 */
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private ClienteRepository clienteRepository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, ClienteRepository clienteRepository) {
        this.tokenService = tokenService;
        this.clienteRepository = clienteRepository;
    }

    /**
     * Recupera o token na requisição, verifica se o mesmo é válido e processa a autenticação.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        /* Recupera o token */
        String token = recuperarToken(request);

        /* Valida o token */
        boolean valido = tokenService.isTokenValido(token);

        /* Se o token for válido, o cliente é autenticado. */
        if (valido) {
            autenticaCliente(token);
        }

        /* Após o processamento, dá continuidade no fluxo da requisição. */
        filterChain.doFilter(request, response);
    }

    /**
     * Este método autentica o cliente.
     */
    private void autenticaCliente(String token) {
        /* Obtém o id do cliente. */
        Long idCliente = tokenService.getIdCliente(token);

        /* Recupera o cliente com base em seu id. */
        Cliente cliente = clienteRepository.findById(idCliente).get();

        /* Dados para autenticação. */
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                cliente,
                null,
                cliente.getAuthorities());

        /* Autentica o cliente. */
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * Método responsável por recuperar o token da requisição.
     */
    private String recuperarToken(HttpServletRequest request) {
        /* Recupera o token do cabeçalho da requisição. */
        String token = request.getHeader("Authorization");

        /* Se o token for nulo, não existir ou não começar com a substring "Bearer " retorna null */
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        /* Se a estrutura do token estiver correta, retorna o token sem o começo "Bearer ". */
        return token.substring(7);
    }
}
