package com.technocorp.testscrud.controller;

import com.technocorp.testscrud.model.People;
import com.technocorp.testscrud.service.PeopleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@AllArgsConstructor
@RequestMapping("/people")
@Api("People Resource")
@CrossOrigin("*")
public class PeopleController {

    PeopleService peopleService;

    @GetMapping
    @ResponseStatus(OK)
    @ApiOperation("Lists all people, find a people by name or part of name")
    public List<People> find(
            @RequestParam(value = "name", required = false)
                    String name) {

        List<People> result;
        if (name == null) {
            result = peopleService.findAll();
        } else {
            result = peopleService.findByName(name);
        }
        return result;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a people")
    public People save(@RequestBody People people) {
        return peopleService.save(people);
    }

    @PutMapping
    @ResponseStatus(OK)
    @ApiOperation("Update a people")
    public People update(@RequestBody People people) {
        return peopleService.update(people);
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete one or two people")
    public void deleteById(@RequestParam(value = "id") String id,
                           @RequestParam(value = "id2", required = false) String id2) {
        peopleService.deleteById(id);
        if (id2 != null) {
            peopleService.deleteById(id2);
        }
    }

}
