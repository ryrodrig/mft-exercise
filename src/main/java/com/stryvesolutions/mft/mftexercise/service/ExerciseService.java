package com.stryvesolutions.mft.mftexercise.service;

import com.stryvesolutions.mft.mftexercise.web.model.ExerciseDTO;
import com.stryvesolutions.mft.mftexercise.web.model.ExercisePagedList;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ExerciseService {

    // Pageable is an interface that holds PageRequest object PageRequest.of(0,10) - page number, number of elements
    // in a single page.
    @Cacheable(cacheNames = "exerciseListCache" , condition = "#refreshCache == false")
    ExercisePagedList getExercises(Pageable pageable, Boolean refreshCache);

    @Cacheable(cacheNames = "exerciseCache" , condition = "#refreshCache == false")
    ExerciseDTO getExerciseById(UUID exerciseId, Boolean refreshCache);

    ExerciseDTO save(ExerciseDTO exerciseDTO);

    void update(UUID id, ExerciseDTO exerciseDTO);

    void deleteById(UUID id);
}
