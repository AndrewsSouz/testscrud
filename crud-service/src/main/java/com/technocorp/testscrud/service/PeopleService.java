package com.technocorp.testscrud.service;

import com.technocorp.testscrud.model.People;
import com.technocorp.testscrud.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class PeopleService {

    PeopleRepository peopleRepository;

    public List<People> findAll() {
        List<People> result = peopleRepository.findAll();
        if (result.isEmpty()) {
            throw new ResponseStatusException(NO_CONTENT, "Banco de dados vazio");
        }
        return result;
    }

    public List<People> findByName(String name) {
        List<People> result = peopleRepository.findByNameIgnoreCaseContaining(name);
        if (result.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Pessoa não encontrada");
        }
        return result;
    }

    public People save(People people) {
        return peopleRepository.save(people);
    }

    public People update(People people) {
        People result;
        if (peopleRepository.existsById(people.getId())) {
            result = peopleRepository.save(people);
        } else {
            throw new ResponseStatusException(BAD_REQUEST, "Pessoa não encontrada");
        }
        return result;
    }

    public void deleteById(String id) {
        boolean exists = peopleRepository.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(BAD_REQUEST, "Id inexistente");
        } else {
            peopleRepository.deleteById(id);
        }
    }

}
