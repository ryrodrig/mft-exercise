package com.stryvesolutions.mft.mftexercise.web.model.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// Example for versioning
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseDTOV2 {

    private UUID id;

    private String exerciseName;

    private String exerciseDescription;

    private BodyPartEnum bodyPart;

    private Boolean equipmentNeeded;
}
