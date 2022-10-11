package com.example.Ej14Testing;

import com.example.Ej14Testing.domain.Persona;
import com.example.Ej14Testing.infrastructure.repository.PersonaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


// Muy útil la página https://www.javaguides.net/2018/09/spring-boot-2-rest-apis-integration-testing.html
//para hacer el ejercicio

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = Ej14TestingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DataJpaTest //esta etiqueta indica que vamos a hacer pruebas unitarias para una entidad
public class PersonaTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    //Se inyecta el repositorio
    @Autowired
    private PersonaRepository personaRepository;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testCrearPersona() {
        Persona persona = new Persona(1, "userTest", "passTest", "NombreTest", "ApellidoTest", "email@company.es", "email@personal.com", "CiudadTest", true, new Date(24/03/2022), "imagen.jpg",  new Date(24/06/2022));
        ResponseEntity<Persona> respuestaPOST = restTemplate.postForEntity(getRootUrl() + "/addperson", persona, Persona.class);
        assertNotNull(respuestaPOST);
        assertNotNull(respuestaPOST.getBody());
        Assertions.assertThat(persona.getId_persona()).isGreaterThan(0);

    }

    @Test
    public void testGetPersonaById() {
        Persona persona = restTemplate.getForObject(getRootUrl() + "/1", Persona.class);
        System.out.println(persona.getName());
        assertNotNull(persona);

    }
    @Test
    public void testAlterPersona() {
        int id = 1;
        //Se recupera la persona a actualizar
        Persona persona = restTemplate.getForObject(getRootUrl() + "/" + id, Persona.class);
        System.out.println("PRE" + persona.getName());
        persona.setName("NuevoNombre");
        System.out.println("SET" + persona.getName());

        //Se actualiza la información de la persona
        restTemplate.put(getRootUrl() + "/alterPersona", persona);
        System.out.println("POST" + persona.getName());

        //Se recupera la persona actualizada
        Persona alteredPersona = restTemplate.getForObject(getRootUrl() + "/" + id, Persona.class);
        System.out.println("ALTERED" + persona.getName());

        assertNotNull(alteredPersona);
        Assertions.assertThat(alteredPersona.getName()).isEqualTo("NuevoNombre");
    }

    @Test
    public void testDeletePersona() {
        int id = 1;
        Persona persona = restTemplate.getForObject(getRootUrl() + "/" + id, Persona.class);
        assertNotNull(persona); //se comprueba que existe la persona antes de borrarla
        restTemplate.delete(getRootUrl() + "/deletePersona/" + id);

        //Una vez borrada, se intenta recuperar la persona con el get by id
        try {
            persona = restTemplate.getForObject(getRootUrl() + "/" + id, Persona.class);
        }
        //Si ha funcionado la eliminación, no se podrá recuperar y saltará una excepción, que tratamos
        catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
    @Test
    public void testGetAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/getall",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }



    @Test
    public void testGuardarPersona(){
        Persona persona = new Persona(1, "userTest", "passTest", "NombreTest", "ApellidoTest", "email@company.es", "email@personal.com", "CiudadTest", true, new Date(24/03/2022), "imagen.jpg",  new Date(24/06/2022));
        personaRepository.save(persona);
        Assertions.assertThat(persona.getId_persona()).isGreaterThan(0);
    }


}
