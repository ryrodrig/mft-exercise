package com.stryvesolutions.mft.mftexercise.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

// initializes spring Application Context with beans that are needed for JSON serialization/deserialization.
// Wouldn't load the complete context.. Similar to WebMvcTest used for controller.
// loads application-snake.properties.
@ActiveProfiles("snake")
@JsonTest
class ExerciseDTOSnakeTest {

    // Object Mapper provides as list of configuration options and methods to perform serialization/deserialization.
    @Autowired
    ObjectMapper objectMapper;

    private ExerciseDTO dummyExerciseDTO() {
        return ExerciseDTO.builder().exerciseName("JSON Serial Test").id(UUID.randomUUID()).exerciseDescription("JSON" +
                " Serializer test").bodyPart("None").equipmentNeeded(false).build();
    }

    @Test
    public void testSerialization() throws JsonProcessingException {
        String jsonStr = objectMapper.writeValueAsString(dummyExerciseDTO());
        System.out.println(jsonStr);
        // snake strategy has "_" for property names.
//        {"id":"a585dc22-d9f1-4f63-a584-2ca3c57f58dd","exercise_name":"JSON Serial Test",
//        "exercise_description":"JSON Serializer test","body_part":"None","equipment_needed":false,
//        "equipments":null,"added_date":null}
    }

}