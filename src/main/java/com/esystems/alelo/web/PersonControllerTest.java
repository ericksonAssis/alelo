package com.esystems.alelo.web;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.esystems.alelo.AleloApplication;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AleloApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonControllerTest {

        private MockMvc mockMvc;

        @Autowired
        private WebApplicationContext wac;

        @Before
        public void setup() {
            this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        }

        @Test
        public void verifyAllPersonList() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/person").accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(4))).andDo(print());
        }

        @Test
        public void verifyPersonById() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/person/3").accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.name").exists())
                    .andExpect(jsonPath("$.id").value(3))
                    .andExpect(jsonPath("$.name").value("Build the artifacts"))
                    .andDo(print());
        }

        @Test
        public void verifyInvalidPersonArgument() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/person/f").accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.errorCode").value(400))
                    .andExpect(jsonPath("$.message").value("The request could not be understood by the server due to malformed syntax."))
                    .andDo(print());
        }

        @Test
        public void verifyInvalidPersonId() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/person/0").accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.errorCode").value(400))
                    .andExpect(jsonPath("$.message").value("The request could not be understood by the server due to malformed syntax."))
                    .andDo(print());
        }

        @Test
        public void verifyNullPerson() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/person/6").accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.errorCode").value(400))
                    .andExpect(jsonPath("$.message").value("The request could not be understood by the server due to malformed syntax."))
                    .andDo(print());
        }

        @Test
        public void verifyDeletePerson() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/person/4").accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.status").value(200))
                    .andExpect(jsonPath("$.message").value("Person has been deleted"))
                    .andDo(print());
        }

        @Test
        public void verifyInvalidPersonIdToDelete() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/person/9").accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.errorCode").value(404))
                    .andExpect(jsonPath("$.message").value("Person to delete doesn´t exist"))
                    .andDo(print());
        }


        @Test
        public void verifySavePerson() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/person/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\" : \"New Person\" }")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.name").exists())
                    .andExpect(jsonPath("$.name").value("New Person"))
                    .andDo(print());
        }

        @Test
        public void verifyMalformedSavePerson() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/person/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"id\": \"8\", \"name\" : \"New Person\' }")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.errorCode").value(404))
                    .andExpect(jsonPath("$.message").value("Payload malformed, id must not be defined"))
                    .andDo(print());
        }

        @Test
        public void verifyUpdatePerson() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.patch("/person/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"id\": \"1\", \"name\" : \"New Person\" }")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.name").exists())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.name").value("New Person"))
                    .andDo(print());
        }

        @Test
        public void verifyInvalidPersonUpdate() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.patch("/person/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"idd\": \"8\", \"text\" : \"New Person\" }")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.errorCode").value(404))
                    .andExpect(jsonPath("$.message").value("Person to update doesn´t exist"))
                    .andDo(print());
        }
}
