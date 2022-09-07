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
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    String adminToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDcxNjA1MywiaWF0IjoxNjU0MTExMjUzLCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.t9fXduLi9sivGeOGpIeGWejTwyMYIYshYIogDxiEUzHPo8NoBffYNyhrGBszX843KxZPuR8LfQS9j1WWWGBjJQ";

    @Autowired
    ObjectMapper objectMapper;
    @Test
    void getAllReviews() throws Exception {
        this.mockMvc.perform(
                        get("/review")
                                .with(user("admin")
                                        .password("admin")
                                        .roles("ADMIN")
                                )
                                .with(csrf())
                                .header(HttpHeaders.AUTHORIZATION,"Bearer "+ adminToken)
                )

                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getReviewsByHardwareCode() throws Exception {

        this.mockMvc.perform(
                        get("/review?code=051803819")
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


}