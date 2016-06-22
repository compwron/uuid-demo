package com.example.component.controller;

import com.example.ExampleFooApi;
import com.example.model.Foo;
import com.example.repository.FooRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@SpringApplicationConfiguration(classes = ExampleFooApi.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class FooControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Autowired
    FooRepository fooRepository;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void retrieveExistingFoo() throws Exception {
        String createJson = new JSONObject()
                .put("bar", "some value")
                .toString();

        MvcResult createResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/foos")
                .content(createJson)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(createResult.getResponse().getStatus(), is(201));

        JsonNode createResponse = new ObjectMapper().readTree(createResult.getResponse().getContentAsString());
        assertThat(createResponse.get("bar").asText(), is("some value CALCULATED"));
        assertThat(createResponse.get("calculatedBar").asText(), is("some value ALSO CALCULATED"));

        String uuid = createResponse.get("uuid").asText();
        assertThat(UUID.fromString(uuid), is(not(nullValue())));

        MvcResult getResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/foos/" + uuid)).andReturn();
        assertThat(getResult.getResponse().getStatus(), is(200));

        JsonNode getResponse = new ObjectMapper().readTree(getResult.getResponse().getContentAsString());
        assertThat(createResponse.get("bar").asText(), is("some value CALCULATED"));
        assertThat(createResponse.get("calculatedBar").asText(), is("some value ALSO CALCULATED"));
        assertThat(getResponse.get("uuid").asText(), is(uuid));
    }

    @Test
    public void uuidIsNotExternallySettable() throws Exception {
        String fakeUuid = UUID.randomUUID().toString();
        String createJson = new JSONObject()
                .put("uuid", fakeUuid)
                .put("bar", "barrrr")
                .toString();

        MvcResult createResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/foos")
                .content(createJson)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        JsonNode createResponse = new ObjectMapper().readTree(createResult.getResponse().getContentAsString());
        String uuid = createResponse.get("uuid").asText();
        assertThat(uuid, is(not(fakeUuid)));
        assertThat(UUID.fromString(uuid), is(not(nullValue())));
    }


    @Test
    public void getBazzesOfFoo() throws Exception {
        String createJson = new JSONObject()
                .put("asdf", "1234")
                .toString();

        String fooUuid = fooRepository.save(new Foo()).getUuid().toString();
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/foos/" + fooUuid + "/bazzes")
                .content(createJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(mvcResult.getResponse().getStatus(), is(201));

        JsonNode createResponse = new ObjectMapper().readTree(mvcResult.getResponse().getContentAsString());
        assertThat(createResponse.get("asdf").asText(), is("1234"));
        assertThat(createResponse.get("calculatedAsdf").asText(), is("2468 CALCULATED"));
    }

    @Test
    public void returnsNotFoundWhenFooIsNotFound() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/foos/" + UUID.randomUUID())).andReturn();
        assertThat(result.getResponse().getStatus(), Is.is(404));
    }
}
