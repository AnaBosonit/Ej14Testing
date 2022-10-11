package com.example.Ej14Testing.application;

import com.example.Ej14Testing.infrastructure.dto.PersonaInputDTO;
import com.example.Ej14Testing.infrastructure.dto.PersonaOutputDTO;

import java.util.List;

public interface PersonaService {

    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception;


    public void deletePersona(int id) throws Exception;

    public PersonaOutputDTO getById(int id) throws Exception;

    public PersonaOutputDTO getByUser(String user);

    public List<PersonaOutputDTO> getAll();

    public PersonaOutputDTO alterPersona(PersonaInputDTO personaInputDTO);
}
