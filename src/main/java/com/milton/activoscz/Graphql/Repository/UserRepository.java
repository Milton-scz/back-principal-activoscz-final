package com.milton.activoscz.Graphql.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.milton.activoscz.Model.User;

public interface UserRepository extends MongoRepository<User, Long> {
    User findByEmail(String email);
    Boolean existsByEmail(String email);
}