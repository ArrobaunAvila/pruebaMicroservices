package com.neoris.pruebamicroservices.api;

import com.neoris.pruebamicroservices.business.ControllerBusiness;
import com.neoris.pruebamicroservices.dto.RequestUserCreateDTO;
import com.neoris.pruebamicroservices.dto.ResponseApiDto;
import com.neoris.pruebamicroservices.services.PublicarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ApiController.class)
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicarService publicarService;

    @MockBean
    private ControllerBusiness controllerBusiness;

    ResponseApiDto responseApiDto = null;

    @Test
    void createUser() throws Exception {
        ArrayList<RequestUserCreateDTO> requestUser = new ArrayList<>();
        when(controllerBusiness.saveUser(requestUser)).thenReturn(responseApiDto);
        mockMvc.perform(get("/gestion/clientes").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    void getAllUsers() throws Exception {
        Mockito.when(controllerBusiness.getALLClients()).thenReturn(responseApiDto);
        mockMvc.perform(get("/gestion/clientes/").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }
}