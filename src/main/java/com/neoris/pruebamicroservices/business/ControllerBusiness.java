package com.neoris.pruebamicroservices.business;

import com.neoris.pruebamicroservices.model.entity.Cliente;
import com.neoris.pruebamicroservices.model.entity.Cuenta;
import com.neoris.pruebamicroservices.model.entity.Movimiento;
import com.neoris.pruebamicroservices.model.entity.Persona;
import com.neoris.pruebamicroservices.services.PublicarService;
import com.neoris.pruebamicroservices.utils.UtilsApp;
import dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class ControllerBusiness {

    @Autowired
    PublicarService publicarService;

    @Autowired
    UtilsApp utils;


    public ResponseApiDto saveUser(ArrayList<RequestUserCreateDTO> requestUserCreateDTO) {
        ResponseApiDto responseApiDto = null;
        List<Object> usersNot = new ArrayList<>(0);
         List<RequestUserCreateDTO> usersCreados = new ArrayList<>();
        try {
               requestUserCreateDTO.forEach(u -> {
                 publicarService.saveClient(Cliente.builder().estado(u.getEstado()).password(u.getContraseña()).persona(
                         publicarService.saveUser(Persona.builder().nombre(u.getNombre()).telefono(u.getTelefono()).direccion(u.getDireccion()).build())
                 ).build());
                 usersCreados.add(u);
               });
                responseApiDto = ResponseApiDto.builder().process(true)
                        .data(usersCreados)
                        .description("Usuarios creados!")
                        .code(HttpStatus.ACCEPTED).build();

        } catch (Exception e) {

            responseApiDto = ResponseApiDto.builder().process(true)
                    .data(null)
                    .description("Error! creando usuarios" + e.getMessage())
                    .code(HttpStatus.BAD_REQUEST).build();

            log.error("Error creando creando users" + e.getMessage());

        }

        return responseApiDto;
    }


    public ResponseApiDto getALLClients(){
          List<Cliente> clients = new ArrayList<>(0);
          ResponseApiDto response = null;
        try {

            try {
                clients =  publicarService.getAllCLients();
            } catch (Exception e){
                 log.error("Fallo obteniendo clients---> getALLClients" + e.getMessage());
            }

            if(clients.isEmpty() || clients.size() == 0){

                    response = ResponseApiDto.builder().process(true)
                            .data(null)
                            .description("Not record clients!")
                            .code(HttpStatus.BAD_REQUEST).build();

            } else {
                    response = ResponseApiDto.builder().process(true)
                            .data(clients)
                            .description("Found Clients!")
                            .code(HttpStatus.BAD_REQUEST).build();
            }
        }catch (Exception e){
            log.error("Error proceso getMovimentsByDateAndName--> " + e.getMessage());
        }
        return response;
    }

    public ResponseApiDto saveCuentas(ArrayList<RequestCuentasDTO> requestCuentasDTOS){

        ResponseApiDto responseApiDto = null;
        List<Object> cuentasNot = new ArrayList<>(0);
         List<RequestCuentasDTO> cuentasCreadas = new ArrayList<>();
        try {
            requestCuentasDTOS.forEach(c -> {
              boolean register =  publicarService.getByName(c.getCliente())
                        .map(person -> {

                            System.out.println(person.getCliente().toString());

                            publicarService.saveCuenta(Cuenta.builder()
                                    .cliente(person.getCliente())
                                    .state(c.getEstado())
                                    .tipoCuenta(c.getTipo())
                                    .saldoInicial(c.getSaldo_inicial())
                                    .numeroCuenta(c.getNumero_cuenta())
                                    .build());

                            return true;
                        }).orElse(false);

              if(register){
                  cuentasCreadas.add(c);
              } else {
                  cuentasNot.add(c);
              }
            });

            responseApiDto = ResponseApiDto.builder().process(true)
                        .data(cuentasCreadas)
                        .description("Cuentas creadas!")
                        .code(HttpStatus.ACCEPTED).build();

        }catch (Exception e){

            responseApiDto = ResponseApiDto.builder().process(true)
                    .data(null)
                    .description("Error! creando cuentas" + e.getMessage())
                    .code(HttpStatus.BAD_REQUEST).build();

            log.error("Error creando cuentas" + e.getMessage());


        }
        return responseApiDto;
    }


    public ResponseApiDto createMovimientos(RequestMovimientoDTO requestMovimientoDTO) {
        ResponseApiDto responseApiDto = null;
        Cuenta cuenta = new Cuenta();
        Movimiento movimiento = new Movimiento();
        Object mov = new Object();
        try {
            if(Objects.nonNull(requestMovimientoDTO) && !requestMovimientoDTO.getTipo_movimiento().equals("") &&
            !requestMovimientoDTO.getValor_movimiento().equals("") && !requestMovimientoDTO.getNumero_cuenta().equals("")){

              cuenta =  publicarService.getByNumeroCuenta(requestMovimientoDTO.getNumero_cuenta().toString());

              String saldo_Actual = cuenta.getSaldoInicial();
              if(requestMovimientoDTO.getTipo_movimiento().equals("retiro")){
                  if(saldo_Actual.equals("0")){
                      responseApiDto =  ResponseApiDto.builder().process(true)
                              .data(cuenta)
                              .description("Saldo no Disponible! Revise su cuenta")
                              .code(HttpStatus.BAD_REQUEST).build();
                  } else {
                      try {
                          movimiento.setTipoMovimiento("retiro");
                          movimiento.setSaldo_disponible(utils.debitoMovimiento(requestMovimientoDTO.getValor_movimiento(), saldo_Actual));
                          movimiento.setFecha(new Date());
                          movimiento.setValor_movimiento(requestMovimientoDTO.getValor_movimiento());
                          movimiento.setDetalle("Retiro de " + requestMovimientoDTO.getValor_movimiento());
                          movimiento.setCuenta(cuenta);
                          publicarService.saveMovimiento(movimiento);
                      } catch (Exception e) {
                        log.error("Error guardando movimiento retiro" + e.getMessage()+ " ----" + e.getCause().getLocalizedMessage());
                      }
                  }
              } else if(requestMovimientoDTO.getTipo_movimiento().equals("deposito")) {
                  try {
                      movimiento.setTipoMovimiento("deposito");
                      movimiento.setSaldo_disponible(utils.depositoMovimiento(requestMovimientoDTO.getValor_movimiento(), saldo_Actual));
                      movimiento.setFecha(new Date());
                      movimiento.setValor_movimiento(requestMovimientoDTO.getValor_movimiento());
                      movimiento.setDetalle("Deposito de " + requestMovimientoDTO.getValor_movimiento());
                      movimiento.setCuenta(cuenta);

                     mov =  publicarService.saveMovimiento(movimiento);
                  } catch (Exception e) {
                      log.error("Error guardando movimiento deposito : "+ e.getMessage() + "--->" + e.getLocalizedMessage());
                  }
              }
                responseApiDto =  ResponseApiDto.builder().process(true)
                              .data(mov)
                              .description("Movimiento Exitoso!")
                              .code(HttpStatus.OK).build();
                 //realizar logica de movimiento
            } else {
            responseApiDto = ResponseApiDto.builder().process(true)
                    .data(null)
                    .description("Por favor! valide datos para realizar movimiento")
                    .code(HttpStatus.BAD_REQUEST).build();
            }

        } catch (Exception e) {
            responseApiDto = ResponseApiDto.builder().process(true)
                    .data(null)
                    .description("Error! creando movimientos" + e.getMessage())
                    .code(HttpStatus.BAD_REQUEST).build();

            log.error("Error creando Movimientos cuentas" + e.getMessage());

        }
        return responseApiDto;
    }

    public ResponseApiDto getMovimentsByDateAndName(String name, String fecha) {
           List<MovimentBillingdto> moviments = new ArrayList<>(0);
           ResponseApiDto responseApiDto = null;
        try {

            if (!name.equals("") && !fecha.equals("")) {
                moviments = publicarService.getAllMovimentsByDateAndName(name, fecha);
                if (!moviments.equals("")) {
                    responseApiDto = ResponseApiDto.builder().process(true)
                            .data(moviments)
                            .description("Listado de moviments...")
                            .code(HttpStatus.BAD_REQUEST).build();
                } else {
                    responseApiDto = ResponseApiDto.builder().process(true)
                            .data(null)
                            .description("No existen movimientos! para busqueda " + name + fecha)
                            .code(HttpStatus.NO_CONTENT).build();
                }
            }

        } catch (Exception e) {
            log.error("Error proceso getMovimentsByDateAndName--> " + e.getMessage());
        }
        return responseApiDto;
    }


    public ResponseApiDto getAllMoviments() {
        ResponseApiDto responseApiDto = null;
        List<MovimentBillingdto> listMoviments = new ArrayList<>(0);
        try {
            listMoviments =   publicarService.getAllMoviments();

            responseApiDto = ResponseApiDto.builder().process(true)
                        .data(listMoviments)
                        .description("Exito! Detalle Movimientos" )
                        .code(HttpStatus.NO_CONTENT).build();

            if (listMoviments.equals(null)) {
                 responseApiDto = ResponseApiDto.builder().process(true)
                        .data(listMoviments)
                        .description("No existen movimientos! para busqueda")
                        .code(HttpStatus.NO_CONTENT).build();

            }
        } catch (Exception e) {
            log.error("Error proceso getAllMoviments--> " + e.getMessage());
        }
        return responseApiDto;
    }

    public ResponseApiDto updateUsuario(RequestBodyClientUpdate requestBodyClientUpdate, Long id){
        Optional<ResponseApiDto> responseApiDto = null;

        try {
            responseApiDto = publicarService.findByIdPersona(id).map(p -> {

                return publicarService.saveUser(Persona.builder().id(id)
                        .identificacion(requestBodyClientUpdate.getInfo().getIdentification())
                        .telefono(requestBodyClientUpdate.getInfo().getTelefono())
                        .direccion(requestBodyClientUpdate.getInfo().getDireccion())
                        .nombre(requestBodyClientUpdate.getInfo().getNombre())
                        .edad(requestBodyClientUpdate.getInfo().getEdad()).build());

            }).map(save -> {
                return ResponseApiDto.builder().process(true)
                        .data(save)
                        .description("Actualizacion exitosa!")
                        .code(HttpStatus.NO_CONTENT).build();
            });

        }catch (Exception e){
            log.error("Error proceso updateClient--> " + e.getMessage());
        }
        return responseApiDto.get();
    }

}
