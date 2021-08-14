package br.com.mekyei.ms.email.controllers;

import br.com.mekyei.ms.email.configurations.security.TokenService;
import br.com.mekyei.ms.email.dtos.CredenciaisDto;
import br.com.mekyei.ms.email.dtos.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controla as requisições de autenticação.
 */
@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    /**
     * Recebe as credenciais de acesso do cliente (email e senha) e submete para autenticação.
     */
    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid CredenciaisDto credenciais) {
        /* Objeto que será passado como parâmetro para o método authenticate. */
        UsernamePasswordAuthenticationToken dadosAutenticacao = credenciais.toUserPswdAuth();

        /* Tenta validar os dados de acesso. Se houver sucesso, devolve o token. Caso contrário, BadRequest. */
        try {
            /* Chama a classe (AutenticacaoService) que irá realizar o processo de autenticação */
            Authentication authentication = authenticationManager.authenticate(dadosAutenticacao);

            /* Gera o token. */
            String token = tokenService.gerarToken(authentication);

            /* Retorna Http Request 200, o token no corpo da requisição e o tipo de autenticação. */
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));

        } catch (AuthenticationException e) {
             return ResponseEntity.badRequest().build();
        }
    }

}
