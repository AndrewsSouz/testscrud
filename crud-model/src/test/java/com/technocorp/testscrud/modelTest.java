package com.technocorp.testscrud;

import com.technocorp.testscrud.model.People;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class ModelTest {


    @Test
    void testCreatedObject() {

        var people = People.builder()
                .id("1")
                .name("Teste")
                .age("30")
                .cpf("020789987-32")
                .build();

        assertEquals("1",people.getId());
        assertEquals("Teste",people.getName());
        assertEquals("30",people.getAge());
        assertEquals("020789987-32",people.getCpf());

    }


}
