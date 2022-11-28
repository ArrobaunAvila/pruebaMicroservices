package com.neoris.pruebamicroservices.api;

import com.neoris.pruebamicroservices.business.ControllerBusiness;
import com.neoris.pruebamicroservices.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/gestion")
public class ApiController {

    private ControllerBusiness controllerBusiness;

    public ApiController(ControllerBusiness controllerBusiness){
    this.controllerBusiness = controllerBusiness;
    }


    @RequestMapping
	@ResponseBody
	public ResponseEntity<String> function(){
		return new ResponseEntity<>("Application gestion usuarios - cuentas --> Run()", HttpStatus.OK);
	}


   @PostMapping("/clientes")
   @ResponseBody
   public ResponseEntity<ResponseApiDto> createUser(@RequestBody ArrayList<RequestUserCreateDTO> requestUserCreateDTO){

       try {
           if (requestUserCreateDTO.isEmpty() || requestUserCreateDTO.size() == 0) {

               return new ResponseEntity(ResponseApiDto.builder().code(HttpStatus.BAD_REQUEST)
                       .description("Error! Incoming data...")
                       .process(false)
                       .data(null).build(), HttpStatus.BAD_REQUEST);
           } else {

               return new ResponseEntity(controllerBusiness.saveUser(requestUserCreateDTO), HttpStatus.OK);
           }

        }catch (Exception e) {
        log.error("Error! found error to createUser --> EndPoint /user" + e.getMessage());
        }
     return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
   }


   @GetMapping("/clientes/")
   public ResponseEntity<ResponseApiDto> getAllUsers(){
          return new ResponseEntity( controllerBusiness.getALLClients(), HttpStatus.OK);
   }
   

   @PutMapping("clientes/{clientId}")
   public ResponseEntity<ResponseApiDto> updateInformationPersonaClient(@RequestBody RequestBodyClientUpdate requestBodyClientUpdate, @PathVariable String clientId){
     return new ResponseEntity( controllerBusiness.updateUsuario(requestBodyClientUpdate, Long.valueOf(clientId)), HttpStatus.OK);
   }

   @RequestMapping(value = "/cuentas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody
   public ResponseEntity<ResponseApiDto> createCuentas(@RequestBody ArrayList<RequestCuentasDTO> requestCuentasDTO){

       try {
           if (requestCuentasDTO.isEmpty() || requestCuentasDTO.size() == 0) {

               return new ResponseEntity(ResponseApiDto.builder().code(HttpStatus.BAD_REQUEST)
                       .description("Error! Incoming data...")
                       .process(false)
                       .data(null).build(), HttpStatus.BAD_REQUEST);
           } else {

               return new ResponseEntity(controllerBusiness.saveCuentas(requestCuentasDTO), HttpStatus.OK);
           }

        }catch (Exception e) {
        log.error("Error! found error to createUser --> EndPoint /user" + e.getMessage());
        }
     return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
   }

   @RequestMapping(value = "/movimientos",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody
   public ResponseEntity<ResponseApiDto> createMovimiento(@RequestBody RequestMovimientoDTO requestMovimientoDTO){
    return new ResponseEntity(controllerBusiness.createMovimientos(requestMovimientoDTO),HttpStatus.OK);
   }

   @GetMapping("/movimientos")
   public ResponseEntity<ResponseApiDto> getMovimentsByClientAndDate(@RequestParam String name, @RequestParam String fecha){
     return new ResponseEntity(controllerBusiness.getMovimentsByDateAndName(name, fecha),HttpStatus.OK);
   }

   @GetMapping("/movimientos/")
   public ResponseEntity<ResponseApiDto> getAllMoviments(){
         return new ResponseEntity(controllerBusiness.getAllMoviments(),HttpStatus.OK);
   }

}
