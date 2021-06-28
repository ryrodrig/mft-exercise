package com.stryvesolutions.mft.mftexercise.bootstrap;

import com.stryvesolutions.mft.mftexercise.domain.Exercise;
import com.stryvesolutions.mft.mftexercise.repositories.ExerciseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// class implementing CommandLineRunner is invoked once the spring context is loaded.
// Mark this class as @component as spring will need to load this class.
@Component
public class ExerciseLoader implements CommandLineRunner {

    private final ExerciseRepository exerciseRepository;

    // constructor auto wiring.
    public ExerciseLoader(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        loadDefaultExercises();
    }

    private void loadDefaultExercises() {
        if(exerciseRepository.count() == 0) {
            exerciseRepository.save(Exercise.builder().exerciseName("Kick Boxing").exerciseDescription("Awesome workout").bodyPart("Full").equipmentNeeded(false).build());
            exerciseRepository.save(Exercise.builder().exerciseName("Running").exerciseDescription("Loose fat").bodyPart("Full").equipmentNeeded(false).build());
        }


        System.out.println(exerciseRepository.count());
    }
}
