package com.stryvesolutions.mft.mftexercise.web.controller;

import com.stryvesolutions.mft.mftexercise.service.ExerciseService;
import com.stryvesolutions.mft.mftexercise.web.model.ErrorDTO;
import com.stryvesolutions.mft.mftexercise.web.model.ExerciseDTO;
import com.stryvesolutions.mft.mftexercise.web.model.ExercisePagedList;
import com.stryvesolutions.mft.mftexercise.web.model.validationGroups.Equipments;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

//    @Autowired
//    public ExerciseController(ExerciseService exerciseService) {
//        this.exerciseService = exerciseService;
//    }

    @GetMapping
    public ResponseEntity<ExercisePagedList> getExercises(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                          @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                          @RequestParam(value = "exerciseName", required = false) String exerciseName,
                                                          @RequestParam(value = "bodyPart", required = false) String bodyPart,
                                                          @RequestParam(value = "refreshCache", required = false,
                                                                  defaultValue = "false") Boolean refreshCache) {
        ExercisePagedList exercisesPageList = exerciseService.getExercises(PageRequest.of(0, 10),refreshCache);
        return ResponseEntity.ok(exercisesPageList);
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity<ExerciseDTO> getExerciseById(@PathVariable("exerciseId") UUID exerciseId,
                                                       @RequestParam(value = "refreshCache", required = false,
                                                               defaultValue = "false") Boolean refreshCache) {
        ExerciseDTO exercise = exerciseService.getExerciseById(exerciseId,refreshCache);
        return ResponseEntity.ok(exercise);
    }

    @PostMapping
    //     @RequestBody is needed to map the request body to the DTO.. If not provided exerciseDTO will be null
    // @Valid - validates the request body using JSR /Bean validation.
    public ResponseEntity<Void> saveExercise(@Valid @RequestBody ExerciseDTO exerciseDTO) {
        ExerciseDTO savedExercise = exerciseService.save(exerciseDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        // Post should return a location header.
        return ResponseEntity.created(URI.create("/v1/api/exercise/"+savedExercise.getId().toString())).build();
    }

    @PutMapping("/{exerciseId}")
//     @RequestBody is needed to map the request body to the DTO.. If not provided exerciseDTO will be null
    public ResponseEntity updateExercise(@PathVariable("exerciseId") UUID id,@Valid @RequestBody ExerciseDTO exerciseDTO) {
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

    @GetMapping("/bodyPart/{bodyPart}")
    public ResponseEntity<ExercisePagedList> retrieveExerciseByBodyPart(@PathVariable("bodyPart") String bodyPart) {
        throw new IllegalArgumentException();
    }

    @GetMapping("/bodyPart/{equipment}")
    // using spring boot @validated method to handle group validation..
    // Would check for the group on the field before applying validation..
    public ResponseEntity<ExercisePagedList> retrieveExerciseByEquipment(@PathVariable("equipment") @Validated(Equipments.class) String equipment) {
        throw new IllegalArgumentException();
    }

    // one way of handling errors is to define exception handlers within the controller.
    // Other option is to define class annotated with @ControllerAdvice and define method that are annotated with @ExceptionHandler
    // Third option with Spring 5 is to use ResponseStatusException to wrap our exception.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleException(MethodArgumentNotValidException exception) {
        ErrorDTO errorDto = ErrorDTO.builder().errorCode("BAD_REQUEST").errorDescription(new ArrayList<>()).build();
        exception.getAllErrors().forEach(e-> {
            errorDto.getErrorDescription().add(e.getDefaultMessage());
        });
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
