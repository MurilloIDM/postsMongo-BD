package com.iftm.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iftm.models.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
