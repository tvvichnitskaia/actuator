package com.example.actuator.actuator.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class MyControllerTest {

    @Mock
    private HealthEndpoint healthEndpoint;

    @InjectMocks
    private MyController myController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(myController).build();
    }

    @Test
    public void testGetHello() throws Exception {
        mockMvc.perform(get("/admin/ping"))
                .andExpect(status().isOk());
    }



    @Test
    public void testCheckHealthStatus() throws Exception {
        // Mocking the behavior of the health endpoint
        when(healthEndpoint.health()).thenReturn(new org.springframework.boot.actuate.health.Health.Builder().status(Status.UP).build());

        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("{\"status\":\"UP\"}", result.getResponse().getContentAsString()));
    }

    @Test
    public void testCheckHealthStatusDown() throws Exception {
        // Mocking a "DOWN" status for health endpoint
        when(healthEndpoint.health()).thenReturn(new org.springframework.boot.actuate.health.Health.Builder().status(Status.DOWN).build());

        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("{\"status\":\"DOWN\"}", result.getResponse().getContentAsString()));
    }
}
