package hr.tvz.konjetic.hardwareapp.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.konjetic.hardwareapp.enums.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class HardwareControllerTest {

    final Long TEST_ID = 1L;
    final String TEST_CODE = "051800941";
    final String TEST_CODE2 = "051800940";
    final String TEST_NAME = "testName";
    final Double TEST_PRICE = 1000.00;
    final String TEST_TYPE = "CPU";
    final String TEST_TYPE2 = "AUTOMOBIL";
    final Integer TEST_AVAILABLE = 1000;

    HardwareCommand hardwareCommand = new HardwareCommand(TEST_NAME, TEST_CODE, TEST_PRICE, TEST_TYPE, TEST_AVAILABLE);
    HardwareCommand hardwareCommand3 = new HardwareCommand(TEST_NAME, TEST_CODE2, TEST_PRICE, TEST_TYPE, TEST_AVAILABLE);
    HardwareCommand hardwareCommand4 = new HardwareCommand(TEST_NAME, TEST_CODE2, TEST_PRICE, TEST_TYPE2, TEST_AVAILABLE);
    HardwareCommand hardwareCommand2 = new HardwareCommand(TEST_NAME, "77777787", TEST_PRICE, TEST_TYPE, TEST_AVAILABLE);




    @Autowired
    private MockMvc mockMvc;

    String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDcxNjA1MywiaWF0IjoxNjU0MTExMjUzLCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.t9fXduLi9sivGeOGpIeGWejTwyMYIYshYIogDxiEUzHPo8NoBffYNyhrGBszX843KxZPuR8LfQS9j1WWWGBjJQ";

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void getAllHardware() throws Exception{
        this.mockMvc.perform(
                get("/hardware")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")
                        )
                 .with(csrf())
                 .header(HttpHeaders.AUTHORIZATION,"Bearer "+ adminToken)
                )

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    void getHardwareByCode() throws Exception {
        this.mockMvc.perform(
                        get("/hardware/051200472")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ adminToken)
                )

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }


    @Test
    @Transactional
    void save() throws Exception {
        this.mockMvc.perform(
                        post("/hardware")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ adminToken)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value(TEST_NAME))
                .andExpect(jsonPath("$.code").value(TEST_CODE))
                .andExpect(jsonPath("$.price").value(TEST_PRICE));
    }

    @Test
    @Transactional
    void saver() throws Exception {
        this.mockMvc.perform(
                        post("/hardware")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ adminToken)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(hardwareCommand4))
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isConflict());
    }

    @Test
    @Transactional
    void update() throws Exception{
        this.mockMvc.perform(
                        put("/hardware/051800940")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(hardwareCommand3))
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }



    @Test
    @Transactional
    void deleteR() throws Exception{
        this.mockMvc.perform(
                        delete("/hardware/051800940")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}