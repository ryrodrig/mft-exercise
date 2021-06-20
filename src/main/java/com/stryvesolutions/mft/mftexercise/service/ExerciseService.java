package com.stryvesolutions.mft.mftexercise.service;

import com.stryvesolutions.mft.mftexercise.web.model.ExerciseDTO;

import java.util.UUID;

public interface ExerciseService {
    ExerciseDTO getExerciseById(UUID exerciseId);

    ExerciseDTO save(ExerciseDTO exerciseDTO);

    void update(UUID id, ExerciseDTO exerciseDTO);

    void deleteById(UUID id);
}
