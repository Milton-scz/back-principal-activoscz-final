package com.milton.activoscz.Resolver;


import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.milton.activoscz.Graphql.Repository.UserRepository;
import com.milton.activoscz.Model.User;
import java.util.List;

@Component @RestController
@Secured("ROLE_ADMIN")
public class UserQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private UserRepository userRepository;
    @QueryMapping 
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}