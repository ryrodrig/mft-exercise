package com.stryvesolutions.mft.mftexercise.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDTO {

    private String errorCode;

    private List<String> errorDescription;


}
