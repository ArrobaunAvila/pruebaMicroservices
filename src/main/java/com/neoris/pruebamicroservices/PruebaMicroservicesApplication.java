package com.neoris.pruebamicroservices;

import com.neoris.pruebamicroservices.model.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.ManagedBean;
import java.util.ArrayList;

@SpringBootApplication
public class PruebaMicroservicesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PruebaMicroservicesApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
    }
}
