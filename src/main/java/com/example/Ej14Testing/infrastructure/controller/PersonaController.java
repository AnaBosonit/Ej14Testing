package com.example.Ej14Testing.infrastructure.controller;

import com.example.Ej14Testing.application.PersonaService;
import com.example.Ej14Testing.errors.NotFoundException;
import com.example.Ej14Testing.infrastructure.dto.PersonaInputDTO;
import com.example.Ej14Testing.infrastructure.dto.PersonaOutputDTO;
import com.example.Ej14Testing.infrastructure.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @Autowired
    PersonaRepository personaRepository;

    /*CRUD*/

    /*create*/
    @CrossOrigin(origins="*")
    @PostMapping("addperson")
    public PersonaOutputDTO addPersona(@RequestBody PersonaInputDTO personaInputDTO) throws Exception {

        return personaService.addPersona(personaInputDTO);

    }


    /*update*/
    @PutMapping("alterPersona")
    public PersonaOutputDTO alterPersona(@RequestBody PersonaInputDTO personaInputDTO) throws Exception {
        try{
            return personaService.alterPersona(personaInputDTO);}
        catch(NoSuchElementException k){
            throw new NotFoundException("Persona con id " + personaInputDTO.getId_persona() + " no encontrada.");
        }

    }
    /*delete*/
    @DeleteMapping("deletePersona/{id}")
    public void deletePersona(@PathVariable int id) throws NotFoundException {
        try{
            personaService.deletePersona(id);}
        catch(NoSuchElementException k){
            throw new NotFoundException("Persona con id " + id + " no encontrada.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*read*/
    /*
     * Buscar por ID */
    /*Al buscar por ID la persona, si es estudiante, return datos de ‘estudiante’, ‘profesores’ y los estudios.
     Si la persona es profesor, return datos de entidad profesor, estudiantes a su cargo, y para cada estudiante los estudios*/
    @GetMapping("{id}")
    public PersonaOutputDTO getById(@PathVariable int id) throws Exception {
        try{
            return personaService.getById(id);}
        catch(NoSuchElementException k){
            throw new NotFoundException("Persona con id " + id + " no encontrada.");
        }
    }


    /*- Buscar por nombre de usuario (campo usuario)*/

    @GetMapping("getByUsuario/{user}")
    public PersonaOutputDTO getByUsuario(@PathVariable String user, @RequestParam(defaultValue="simple") String outputType){
        return personaService.getByUser(user);

    }

    /*- Mostrar todos los registros.*/
    @CrossOrigin(origins="*")
    @GetMapping("getall")
    public List<PersonaOutputDTO> getAll(@RequestParam(defaultValue="simple") String outputType){

        return personaService.getAll();
    }



}
