package com.neoris.pruebamicroservices;

import com.neoris.pruebamicroservices.model.entity.Persona;
import com.neoris.pruebamicroservices.model.repository.IPersonaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class PruebaMicroservicesApplication implements CommandLineRunner {

    private IPersonaRepository iPersonaRepository;

    public PruebaMicroservicesApplication(IPersonaRepository iPersonaRepository){
    this.iPersonaRepository = iPersonaRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PruebaMicroservicesApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
    }
}
