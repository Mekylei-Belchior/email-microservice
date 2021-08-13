package br.com.mekyei.ms.email.configurations.security;

import br.com.mekyei.ms.email.models.Cliente;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    /* Injeta a sequência de caracteres contida na propriedade do application.properties. */
    @Value("${email.jwt.secret}")
    private String secret;

    /* Inteja a quantidade de milisegundos contido na propriedade do application.properties. */
    @Value("${email.jwt.expiration}")
    private String expiration;


    /**
     * Gera o token utilizando o JWT.
     */
    public String gerarToken(Authentication authentication) {
        /* Obtém o cliente */
        Cliente clienteLogado = (Cliente) authentication.getPrincipal();

        /* Define a data atual e o tempo de validade do token. */
        Date hoje = new Date();
        Date expira = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder() /* Utilizado para setar as informações e criar o token. */
                .setIssuer("Microserviço de E-mail") /* Nome da aplicação que está gerando o token. */
                .setSubject(String.valueOf(clienteLogado.getId())) /* ID do cliente autenticado que irá receber o token. */
                .setIssuedAt(hoje) /* Data em que o token foi criado. */
                .setExpiration(expira) /* Momento em que o token expira. */
                .signWith(SignatureAlgorithm.HS256, secret) /* Algoritmo para criptografar o token e a senha para fazer a assinatura. */
                .compact() /* Compacta em uma string. */;
    }
}
