package com.stryvesolutions.mft.mftexercise.web.controller;

import com.stryvesolutions.mft.mftexercise.service.ExerciseService;
import com.stryvesolutions.mft.mftexercise.web.model.ExerciseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// @WebMvcTest is used to test only the Weblayer.. classes with @controller other annotations are not loaded by spring boot.
//if there are multiple controllers you can filter only the components needed by passing an argument as in case below.
// one of the approach is to test without starting the server, where spring would handle all incoming http requests
// and hand it off to the controller. this would mimic an actual http call.
// To do that Spring provide MockMvc.

@WebMvcTest(ExerciseController.class)
class ExerciseControllerTest {

    @Autowired
    MockMvc mockMvc;

    // Since @WebMVCTest only loads web layer, any service layer dependencies should be mocked..
    // Use @MockBean to provide instance of the bean..
    // usually mock repository beans.
    @MockBean
    ExerciseService exerciseService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getExerciseById() throws Exception {
        when(exerciseService.getExerciseById(any(),any())).thenReturn(ExerciseDTO.builder().exerciseName("testExercise").exerciseDescription("Test Exercise").equipmentNeeded(false).build());
        mockMvc.perform(get("/v1/api/exercise/" + UUID.randomUUID().toString())).andExpect(status().isOk());
    }

    @Test
    void saveExercise() {
    }

    @Test
    void updateExercise() {
    }

    @Test
    void deleteExercise() {
    }
}