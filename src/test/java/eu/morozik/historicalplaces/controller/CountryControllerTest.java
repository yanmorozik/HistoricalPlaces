package eu.morozik.historicalplaces.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.morozik.historicalplaces.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static eu.morozik.historicalplaces.prototype.CountryPrototype.aCountryDto;
import static eu.morozik.historicalplaces.prototype.CountryPrototype.aCountryDtoFindAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CountryControllerTest {

    MockMvc mockMvc;
    ObjectMapper objectMapper;

    @Mock
    CountryService countryService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CountryController(countryService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void save() throws Exception {
        when(countryService.save(any())).thenReturn(aCountryDto());
        mockMvc.perform(post("/countries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aCountryDto())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(aCountryDto())))
                .andExpect(jsonPath("$.name").value("test"));
    }

    @Test
    void findById() throws Exception {
        when(countryService.findById(any())).thenReturn(aCountryDto());
        mockMvc.perform(get("/countries/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aCountryDto())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(aCountryDto())))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void findAll() throws Exception {
        int page = 4, size = 3;
        String name = "name";
        when(countryService.findAll(page, size, name)).thenReturn(Collections.singletonList(aCountryDtoFindAll()));
        mockMvc.perform(get("/countries?page=" + page + "&size=" + size + "&name=" + name)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(aCountryDtoFindAll()))))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {

        mockMvc.perform(put("/countries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aCountryDto())))
                .andExpect(status().isOk());
    }


    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/countries/" + aCountryDto().getId()))
                .andExpect(status().isNoContent());
    }
}
