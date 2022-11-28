package com.neoris.pruebamicroservices.utils;

import org.springframework.stereotype.Component;

@Component
public class UtilsApp {

    public String debitoMovimiento(String valorMovimiento, String valorActual){
        Double resultado = 0.0000;
        String result = null;
        try {
          resultado =   Double.valueOf(valorMovimiento) - Double.valueOf(valorActual);
        }catch (Exception e){}
        if(String.valueOf(resultado).substring(0).equals("-")){
            result = String.valueOf(resultado).substring(1);
        } else {
            result = String.valueOf(resultado);
        }
        return result;
    }

    public String depositoMovimiento(String valorMovimiento, String valorActual){
        Double resultado = 0.0000;
        try {
          resultado =   Double.valueOf(valorMovimiento) + Double.valueOf(valorActual);

        }catch (Exception e){}
        return String.valueOf(resultado);
    }

}
