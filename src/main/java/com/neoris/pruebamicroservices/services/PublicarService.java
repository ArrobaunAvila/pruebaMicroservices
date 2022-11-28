package com.neoris.pruebamicroservices.services;

import com.neoris.pruebamicroservices.model.entity.Cliente;
import com.neoris.pruebamicroservices.model.entity.Cuenta;
import com.neoris.pruebamicroservices.model.entity.Movimiento;
import com.neoris.pruebamicroservices.model.entity.Persona;
import com.neoris.pruebamicroservices.model.repository.*;
import com.neoris.pruebamicroservices.dto.MovimentBillingdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicarService {

    private IClienteRepository clienteRepository;

    private IPersonaRepository personaRepository;

    private ICuentaRepository cuentaRepository;

    @Autowired
    private IMovimientoRepository movimientoRepository;

    @Autowired
    MovimentsDao movimentsDao;

    @Autowired
    public PublicarService(IClienteRepository clienteRepository , IPersonaRepository personaRepository, ICuentaRepository cuentaRepository){
        this.personaRepository = personaRepository;
        this.clienteRepository = clienteRepository;
        this.cuentaRepository = cuentaRepository;
    }


    public Cliente saveClient(Cliente cliente){return clienteRepository.saveAndFlush(cliente);}

    public Optional<Cliente> findByIdClient(Long id){
        return clienteRepository.findById(id);
    }

    public Optional<Persona> findByIdPersona(long id){
        return personaRepository.findById(id);
    }

    public Persona saveUser(Persona persona){return personaRepository.saveAndFlush(persona);}

    public Optional<Persona> getByName(String name){return personaRepository.findByNombre(name);}

    public Cuenta saveCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta getByNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta);
    }

    public Movimiento saveMovimiento(Movimiento movimiento) {
      return movimientoRepository.saveAndFlush(movimiento);
    }

   public List<MovimentBillingdto> getAllMovimentsByDateAndName(String name, String date){return movimentsDao.findAllMovimentsByNameAndDate(name, date);}

    public List<MovimentBillingdto> getAllMoviments(){return movimentsDao.findAllMoviments();}

    public List<Cliente>  getAllCLients(){return clienteRepository.findAll();}
}
