package com.stryvesolutions.mft.mftexercise.service;

import com.stryvesolutions.mft.mftexercise.web.model.ExerciseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ExerciseServiceImpl implements ExerciseService {

    @Override
    public ExerciseDTO getExerciseById(UUID exerciseId) {
        return ExerciseDTO.builder().id(exerciseId).exerciseName("Kickboxing").exerciseDescription("Wonderful Cardio").bodyPart("All").equipmentNeeded(Boolean.FALSE).build();
    }

    @Override
    public ExerciseDTO save(ExerciseDTO exerciseDTO) {
//        return ExerciseDTO.builder().id(UUID.randomUUID()).exerciseName(exerciseDTO.getExerciseName()).exerciseDescription(exerciseDTO.getExerciseDescription()).bodyPart("Full").equipmentNeeded(Boolean.FALSE).build();
        exerciseDTO.setId(UUID.randomUUID());
        return exerciseDTO;
    }

    @Override
    public void update(UUID id, ExerciseDTO exerciseDTO) {
        // Update database.
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("Delete an exercise...");
    }


}
