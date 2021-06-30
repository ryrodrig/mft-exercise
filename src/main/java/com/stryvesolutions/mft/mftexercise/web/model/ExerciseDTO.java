package com.stryvesolutions.mft.mftexercise.web.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stryvesolutions.mft.mftexercise.web.model.serializer.CustomDateDeserializer;
import com.stryvesolutions.mft.mftexercise.web.model.validationGroups.Equipments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseDTO implements Serializable {

    // Override Property name. - Jackson facility.
    @JsonProperty("exerciseId")
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

    // Formats output in a specific pattern and type.
//    addedDate":"2021-06-28
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    // Another option is to create a customDateSerializer class that extends Jackson JsonSerializer class and
//    @JsonSerialize(using = customDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private OffsetDateTime addedDate;


}
