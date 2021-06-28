package com.stryvesolutions.mft.mftexercise.repositories;

import com.stryvesolutions.mft.mftexercise.domain.Exercise;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

// Spring data repositories.. Provides paging and sorting capabilities
public interface ExerciseRepository extends PagingAndSortingRepository<Exercise, UUID> {
}
