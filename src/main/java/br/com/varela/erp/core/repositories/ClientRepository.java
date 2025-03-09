package br.com.varela.erp.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.varela.erp.core.models.Client;

public interface ClientRepository  extends JpaRepository<Client, Long>{
    
}
