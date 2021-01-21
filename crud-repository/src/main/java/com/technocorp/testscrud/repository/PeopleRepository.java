package com.technocorp.testscrud.repository;

import com.technocorp.testscrud.model.People;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends MongoRepository<People, String> {
    List<People> findByNameIgnoreCaseContaining(String name);
}

