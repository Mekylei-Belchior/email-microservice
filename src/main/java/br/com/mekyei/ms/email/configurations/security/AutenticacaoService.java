package br.com.mekyei.ms.email.configurations.security;

import br.com.mekyei.ms.email.models.Cliente;
import br.com.mekyei.ms.email.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe que possui a lógica de autenticação.
 */
@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    ClienteRepository clienteRepository;

    /**
     * Este método verifica os dados de acesso do cliente.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /* Busca os dados do cliente na base de dados. */
        Optional<Cliente> cliente = clienteRepository.findByEmail(username);

        /* Verifica se os dados do cliente foram encontrados na base de dados. */
        if (cliente.isPresent()) {
            /* Retorna os dados do cliente. */
            return cliente.get();
        }

        /* Caso os dados do cliente não tenha sido encotrados é lançado uma excessão. */
        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
