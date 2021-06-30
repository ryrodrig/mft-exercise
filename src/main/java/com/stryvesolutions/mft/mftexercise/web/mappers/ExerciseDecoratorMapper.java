package com.stryvesolutions.mft.mftexercise.web.mappers;

import com.stryvesolutions.mft.mftexercise.domain.Exercise;
import com.stryvesolutions.mft.mftexercise.web.model.ExerciseDTO;
import lombok.Setter;

import java.time.OffsetDateTime;

// Usually create a decorator class when we need to add additional logic or set an additional variable to an object.
public abstract class ExerciseDecoratorMapper implements ExerciseMapper{

    @Setter
    private ExerciseMapper exerciseMapper;

    @Override
    public Exercise convertToExercise(ExerciseDTO exerciseDTO) {
        return exerciseMapper.convertToExercise(exerciseDTO);
    }

    @Override
    public ExerciseDTO convertToExerciseDTO(Exercise exercise) {
        ExerciseDTO exerciseDTO = exerciseMapper.convertToExerciseDTO(exercise);
        // usually 3rd party calls and populate the details
        exerciseDTO.setAddedDate(OffsetDateTime.now());
        return exerciseDTO;
    }
}
