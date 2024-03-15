package com.ibm.nfvodriver.web.alm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AvailabilityController.class)
public class AvailabilityControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(authorities="USER")
    public void testAvailability() throws Exception {
        mvc.perform(get("/api").accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(content().string("OK"));
    }

}