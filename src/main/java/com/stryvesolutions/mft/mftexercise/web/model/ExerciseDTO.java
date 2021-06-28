package com.stryvesolutions.mft.mftexercise.web.model;


import com.stryvesolutions.mft.mftexercise.web.model.validationGroups.Equipments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseDTO {

    private UUID id;

    // JSR/Bean validation
    // Group is set to "Default.class"
    @NotBlank
    private String exerciseName;

    @NotBlank
    private String exerciseDescription;

    @NotBlank
    private String bodyPart;

    private Boolean equipmentNeeded;

    // evaluated if Validator.validate(<obje> , Equipments.class) or spring boots @Validated(Equipment.class) is used.
    @NotEmpty(groups = Equipments.class)
    private List<String> equipments;

    private OffsetDateTime addedDate;
}
