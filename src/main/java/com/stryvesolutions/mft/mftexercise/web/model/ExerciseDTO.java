package com.stryvesolutions.mft.mftexercise.web.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseDTO {

    private UUID id;

    private String exerciseName;

    private String exerciseDescription;

    private String bodyPart;

    private Boolean equipmentNeeded;
}
