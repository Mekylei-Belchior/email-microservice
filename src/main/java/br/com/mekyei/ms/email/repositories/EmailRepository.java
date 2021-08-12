package br.com.mekyei.ms.email.repositories;

import br.com.mekyei.ms.email.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {
}
