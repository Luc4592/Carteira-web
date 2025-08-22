package com.example.portfolio.controller;

import com.example.portfolio.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // Inicia o contexto Spring Boot para testes
@AutoConfigureMockMvc // Configura o MockMvc para simular requisições HTTP
@ActiveProfiles("test")
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc; // Permite simular requisições HTTP para os endpoints

    @Autowired
    private ObjectMapper objectMapper; // Serializa objetos Java para JSON

    @Test
    public void testLoginComSucesso() throws Exception {
        // Cria um objeto com usuário e senha válidos
        var loginRequest = new LoginRequest();
        loginRequest.setUsername("teste");
        loginRequest.setPassword("teste");

        // Executa uma requisição POST para /auth/login com o JSON do loginRequest
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk()); // Espera que o status seja 200 (OK)
    }

    @Test
    public void testLoginComFalha() throws Exception {
        // Cria um objeto com usuário ou senha inválidos
        var loginRequest = new LoginRequest();
        loginRequest.setUsername("usuario_invalido");
        loginRequest.setPassword("senha_errada");

        // Executa uma requisição POST para /auth/login com o JSON do loginRequest
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized()); // Espera que o status seja 401 (Unauthorized)
    }

    @Test
    public void testCadastroELoginComSucesso() throws Exception {
        // Cria o objeto de cadastro
        var loginRequest = new LoginRequest();
        loginRequest.setUsername("teste");
        loginRequest.setPassword("teste");

        // Faz o cadastro do usuário via endpoint /auth/register
        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk());

        // Agora faz o login com o mesmo usuário
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk());
    }
}