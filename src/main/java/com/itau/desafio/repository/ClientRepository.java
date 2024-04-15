package com.itau.desafio.repository;

import com.itau.desafio.repository.cache.ClientCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<ClientCache, String> {
}