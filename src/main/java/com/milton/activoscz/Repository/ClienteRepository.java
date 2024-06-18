package com.milton.activoscz.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.milton.activoscz.Model.Cliente;


public interface ClienteRepository  extends MongoRepository<Cliente, Long>{
	public Cliente findByClienteId(Long clienteId);
	Boolean existsByClienteId(Long clienteId);
}