package com.stryvesolutions.mft.mftexercise.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.time.OffsetDateTime;
import java.util.UUID;

// initializes spring Application Context with beans that are needed for JSON serialization/deserialization.
// Wouldn't load the complete context.. Similar to WebMvcTest used for controller.
@JsonTest
class ExerciseDTOTest {

    // Object Mapper provides as list of configuration options and methods to perform serialization/deserialization.
    @Autowired
    ObjectMapper objectMapper;

    private ExerciseDTO dummyExerciseDTO() {
        return ExerciseDTO.builder().exerciseName("JSON Serial Test").id(UUID.randomUUID()).exerciseDescription("JSON" +
                " Serializer test").bodyPart("None").addedDate(OffsetDateTime.now()).equipmentNeeded(false).build();
    }

    @Test
    public void testSerialization() throws JsonProcessingException {
        String jsonStr = objectMapper.writeValueAsString(dummyExerciseDTO());
        System.out.println(jsonStr);
    }

    @Test
    public void testDeserialize() throws JsonProcessingException {
        String jsonStr = "{\"exerciseName\":\"JSON Serial Test\",\"exerciseDescription\":\"JSON Serializer test\",\"bodyPart\":\"None\",\"equipmentNeeded\":false,\"equipments\":null,\"addedDate\":\"2021-06-28\",\"exerciseId\":\"b670c7f8-8b5e-414c-a4a4-745bc4fda5a1\"}";
        ExerciseDTO exerciseDTO = objectMapper.readValue(jsonStr, ExerciseDTO.class);
    }


}