package br.com.mekyei.ms.email.dtos;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Classe responsável pela representação dos dados de acesso do cliente recebidos pelo controller.
 */
public class CredenciaisDto {

    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Converte as credenciais.
     * @return um objeto UsernamePasswordAuthenticationToken.
     */
    public UsernamePasswordAuthenticationToken toUserPswdAuth() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }
}
