package com.example.Ej14Testing.application;

import com.example.Ej14Testing.domain.Persona;
import com.example.Ej14Testing.infrastructure.dto.PersonaOutputDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
@WebAppConfiguration
@SpringBootTest*/
class PersonaServiceImplTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @Autowired
    PersonaService personaService;


    @BeforeEach
    void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

    }


    @Test
    void addPersonaTest() throws Exception {
        String payload = "";
        mvc.perform(post("/addperson")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void deletePersona() {
    }

    @Test
    void getById() throws Exception {
        final Integer id =1;
        final Persona persona = new Persona(1, "user", "123", "Paco", "Leon", "a@gmail.com", "b@gmail.com", "Jaen", true, new Date(), "a.jpg", new Date());
        given(personaService.getById(id)).willReturn(persona);

        this.mvc.perform(post("").content()).andExpect(jsonPath(new Date("$.created_date").))
                .

    }

    @Test
    void getByUser() {
    }

    @Test
    void getAll() {
    }

    @Test
    void alterPersona() {
    }
}