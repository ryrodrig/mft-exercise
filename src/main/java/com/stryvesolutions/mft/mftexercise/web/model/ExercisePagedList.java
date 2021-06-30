package com.stryvesolutions.mft.mftexercise.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ExercisePagedList extends PageImpl<ExerciseDTO> implements Serializable {


    public ExercisePagedList(List<ExerciseDTO> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public ExercisePagedList(List<ExerciseDTO> content) {
        super(content);
    }
}
