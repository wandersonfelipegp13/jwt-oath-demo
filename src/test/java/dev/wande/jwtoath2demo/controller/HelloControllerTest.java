package dev.wande.jwtoath2demo.controller;

import dev.wande.jwtoath2demo.config.SecurityConfig;
import dev.wande.jwtoath2demo.model.LoginRequest;
import dev.wande.jwtoath2demo.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({HelloController.class, AuthController.class})
@Import({SecurityConfig.class, TokenService.class})
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void rootWhenUnauthenticatedThen401() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void tokenWithBasicThenGetToken() throws Exception {

        MvcResult result = this.mvc.perform(
                        post("/token")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"username\":\"wande\", \"password\": \"password\"}"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isNotEmpty();

    }

    @Test
    void rootWhenAuthenticatedThenSaysHelloUser() throws Exception {

        MvcResult result = this.mvc.perform(
                        post("/token")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"username\":\"wande\", \"password\": \"password\"}"))
                .andExpect(status().isOk())
                .andReturn();

        String token = result.getResponse().getContentAsString();

        this.mvc.perform(get("/")
                        .header("Authorization", "Bearer " + token))
                .andExpect(content().string("Hello, wande"));
    }

    @Test
    @WithMockUser
    void rootWithMockUserStatusIsOk() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

}