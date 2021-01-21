package com.technocorp.testscrud.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.technocorp.testscrud.model.People;
import com.technocorp.testscrud.service.PeopleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PeopleController.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = PeopleController.class)
class PeopleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PeopleService peopleService;

    People people;
    ObjectWriter mapper;
    String json;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        people = People.builder()
                .name("Teste")
                .build();

        mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
        json = mapper.writeValueAsString(people);
    }

    @Test
    void whenFindWithoutParam_ThenTestReturnStatusAndTypeOfReturn() throws Exception {
        mockMvc.perform(get("/people"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void whenFindWithParam_ThenTestReturnStatusAndTypeOfReturn() throws Exception {
        List<People> response = new ArrayList<>();
        response.add(people);
        when(peopleService.findByName("Teste")).thenReturn(response);
        mockMvc.perform(get("/people")
                .param("name", "Teste"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void whenSave_ThenTestReturnStatusAndTypeOfReturn() throws Exception {
        when(peopleService.save(people)).thenReturn(people);
        mockMvc.perform(post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void whenUpdate_ThenTestReturnStatusAndTypeOfReturn() throws Exception {
        when(peopleService.save(people)).thenReturn(people);
        mockMvc.perform(put("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void whenDeleteWithOneParam_ThenTestReturnStatus() throws Exception {
        mockMvc.perform(delete("/people")
                .param("id","1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDeleteWithTwoParam_ThenTestReturnStatus() throws Exception {
        mockMvc.perform(delete("/people")
                .param("id","1")
                .param("id2","2"))
                .andExpect(status().isNoContent());
    }


}
