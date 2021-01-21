package com.technocorp.testscrud.service;


import com.technocorp.testscrud.model.People;
import com.technocorp.testscrud.repository.PeopleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeopleServiceTest {

    @Mock
    PeopleRepository peopleRepository;

    @InjectMocks
    PeopleService peopleService;

    People people = People.builder()
            .id("1")
            .name("Teste")
            .age("10")
            .cpf("123")
            .build();

    People people2 = People.builder()
            .id("2")
            .name("Teste1")
            .age("110")
            .cpf("321")
            .build();


    @Test
    void whenFindAll_thenTestReturnPeople() {
        List<People> response = new ArrayList<>();
        response.add(people);
        response.add(people2);

        when(peopleRepository.findAll()).thenReturn(response);

        var stubExpect = response;
        var stubActual = peopleService.findAll();

        assertNotNull(response);
        assertEquals(stubExpect, stubActual);
    }

    @Test
    void whenFindAll_thenTestThrowException() {
        List<People> response = new ArrayList<>();
        when(peopleRepository.findAll()).thenReturn(response);
        assertThrows(ResponseStatusException.class, () ->  peopleService.findAll());
    }

    @Test
    void whenFindByName_thenTestResponse() {
        List<People> response = new ArrayList<>();
        response.add(people);
        when(peopleRepository.findByNameIgnoreCaseContaining("Teste")).thenReturn(response);

        var stubExpect = response;
        var stubActual = peopleService.findByName("Teste");

        assertNotNull(stubActual);
        assertEquals(stubExpect, stubActual);
    }

    @Test
    void whenFindByName_thenTestThrowException() {
        List<People> response = new ArrayList<>();
        when(peopleRepository.findByNameIgnoreCaseContaining("")).thenReturn(response);
        assertThrows(ResponseStatusException.class, () -> peopleService.findByName(""));
    }


    @Test
    void whenSave_thenTestIfPeopleWasSaved() {
        when(peopleRepository.save(people)).thenReturn(people);

        var stubExpected = people;
        var stubActual = peopleService.save(people);

        assertEquals(stubExpected, stubActual);
    }

    @Test
    void whenUpdate_thenTestIfPeopleWasUpdated() {
        when(peopleRepository.existsById("1")).thenReturn(true);
        when(peopleRepository.save(people)).thenReturn(people);

        var stubExpected = people;
        var stubActual = peopleService.update(people);

        assertEquals(stubExpected, stubActual);
    }

    @Test
    void whenUpdateIdIsNotPresent_thenTestIfExceptionIsThrowed() {
        when(peopleRepository.existsById("1")).thenReturn(false);
        assertThrows(ResponseStatusException.class, () -> peopleService.update(people));
    }


    @Test
    void whenDelete_thenTestIfPeopleWasDeleted(){
        when(peopleRepository.existsById("1")).thenReturn(true);
        peopleService.deleteById("1");
        verify(peopleRepository,times(1)).deleteById("1");
    }

    @Test
    void whenDelete_thenTestIfExceptionIsThrowed(){
        when(peopleRepository.existsById("1")).thenReturn(false);
        assertThrows(ResponseStatusException.class,() -> peopleService.deleteById("1"));
    }
}
