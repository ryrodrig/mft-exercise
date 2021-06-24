package com.stryvesolutions.mft.mftexercise.web.controller;

import com.stryvesolutions.mft.mftexercise.service.ExerciseService;
import com.stryvesolutions.mft.mftexercise.web.model.ExerciseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity<ExerciseDTO> getExerciseById(@PathVariable("exerciseId") UUID exerciseId) {
        ExerciseDTO exercise = exerciseService.getExerciseById(exerciseId);
        return ResponseEntity.ok(exercise);
    }

    @PostMapping
    //     @RequestBody is needed to map the request body to the DTO.. If not provided exerciseDTO will be null
    public ResponseEntity<Void> saveExercise(@RequestBody ExerciseDTO exerciseDTO) {
        ExerciseDTO savedExercise = exerciseService.save(exerciseDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        // Post should return a location header.
        return ResponseEntity.created(URI.create("/v1/api/exercise/"+savedExercise.getId().toString())).build();
    }

    @PutMapping("/{exerciseId}")
//     @RequestBody is needed to map the request body to the DTO.. If not provided exerciseDTO will be null
    public ResponseEntity updateExercise(@PathVariable("exerciseId") UUID id,@RequestBody ExerciseDTO exerciseDTO) {
        exerciseService.update(id, exerciseDTO);
        // ideal to return noContent is all was well (204).
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{exerciseId}")
    // Another way to return status code of 204 if all was successful.
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExercise(@PathVariable("exerciseId") UUID id) {
        exerciseService.deleteById(id);
    }
}
