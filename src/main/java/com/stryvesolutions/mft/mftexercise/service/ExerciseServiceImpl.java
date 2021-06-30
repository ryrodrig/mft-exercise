package com.stryvesolutions.mft.mftexercise.service;

import com.stryvesolutions.mft.mftexercise.domain.Exercise;
import com.stryvesolutions.mft.mftexercise.repositories.ExerciseRepository;
import com.stryvesolutions.mft.mftexercise.web.mappers.ExerciseMapper;
import com.stryvesolutions.mft.mftexercise.web.model.ExerciseDTO;
import com.stryvesolutions.mft.mftexercise.web.model.ExercisePagedList;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

// Creates arg constructor for instance variables marked final.
@RequiredArgsConstructor
@Service
@Slf4j
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    private final ExerciseMapper exerciseMapper;

    @Override
    // Pageable is an interface that holds PageRequest object PageRequest.of(0,10) - page number, number of elements
    // in a single page.
    // Cacheable annotation to cache response.. if condition is met , the data is retrieved from the cache.
    // cacheName is defined within ehCache.xml file where the configurations are specified.
    @Cacheable(cacheNames = "exerciseListCache" , condition = "#refreshCache == false")
    public ExercisePagedList getExercises(Pageable pageable, Boolean refreshCache) {
        log.info("Retrieving exercise list from database.");
        Page<Exercise> exercisePage = exerciseRepository.findAll(pageable);
        return new ExercisePagedList(exercisePage.getContent().stream().map(exerciseMapper::convertToExerciseDTO).collect(Collectors.toList()), PageRequest.of(exercisePage.getPageable().getPageNumber(),exercisePage.getPageable().getPageSize()), exercisePage.getTotalElements());
    }

    @Override
    // Cacheable annotation to cache response.. if condition is met , the data is retrieved from the cache else
    // retrieved from the database.
    // cacheName is defined within ehCache.xml file where the configurations are specified.
    @Cacheable(cacheNames = "exerciseCache" , condition = "#refreshCache == false")
    public ExerciseDTO getExerciseById(UUID exerciseId,Boolean refreshCache) {
        log.info("Retrieving exercise from database.");
//        return ExerciseDTO.builder().id(exerciseId).exerciseName("Kickboxing").exerciseDescription("Wonderful Cardio").bodyPart("All").equipmentNeeded(Boolean.FALSE).build();
        return exerciseMapper.convertToExerciseDTO(exerciseRepository.findById(exerciseId).orElseThrow(() -> new RuntimeException()));
    }

    @Override
    public ExerciseDTO save(ExerciseDTO exerciseDTO) {
//        return ExerciseDTO.builder().id(UUID.randomUUID()).exerciseName(exerciseDTO.getExerciseName()).exerciseDescription(exerciseDTO.getExerciseDescription()).bodyPart("Full").equipmentNeeded(Boolean.FALSE).build();
        return exerciseMapper.convertToExerciseDTO(exerciseRepository.save(exerciseMapper.convertToExercise(exerciseDTO)));
    }

    @Override
    public void update(UUID exerciseId, ExerciseDTO exerciseDTO) {
        // Update database.
        Exercise persistedExercise =
                exerciseRepository.findById(exerciseId).orElseThrow(() -> new RuntimeException());

        exerciseRepository.save(exerciseMapper.convertToExercise(exerciseDTO));


    }

    @Override
    public void deleteById(UUID id) {
        log.debug("Delete an exercise...");
    }


}
