package com.milton.activoscz.Resolver;


import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import com.milton.activoscz.Model.Cliente;
import com.milton.activoscz.Model.ClientePage;
import com.milton.activoscz.Model.PageInfo;
import com.milton.activoscz.Repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Component @RestController

public class ClienteQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @QueryMapping 
    public ClientePage getAllClientes(@Argument int page,@Argument int size) {
    	 Pageable pageable = PageRequest.of(page, size);
    	 Page<Cliente> clientePage = clienteRepository.findAll(pageable);
    	 PageInfo pageInfo = new PageInfo(clientePage.getTotalPages(), clientePage.getTotalElements(), page, size);
    	 return new ClientePage(pageInfo, clientePage.getContent());
    }
    
    @QueryMapping
    public Cliente findByClienteId(@Argument long clienteId) {
        return clienteRepository.findByClienteId(clienteId);
       
    }
    
    @QueryMapping
    public Boolean existsClienteId(@Argument long clienteId) {
        return clienteRepository.existsByClienteId(clienteId);
       
    }
}