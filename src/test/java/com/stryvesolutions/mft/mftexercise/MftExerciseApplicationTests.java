package com.stryvesolutions.mft.mftexercise;

import com.stryvesolutions.mft.mftexercise.service.ExerciseService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.TestPropertySource;
// Usually used to perform Integration testing.
// SpringBootTest annotation tells spring to look for the main configuration class (one with @SpringBootApplication )
// and use that to bootstrap the complete Spring container. This test is only to check if the context is
// loaded or not.

// webEnvironment.MOCK can be used to start a mock servlet environment.
//@SpringBootTest(SpringBootTest.WebEnvironment.MOCK)
// can be used to override application.properties
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
// @Runwith(SpringRunner.class) until Junit 4 needed to be added to enable spring features like autowire.
// With Junit5 Runwith is replaced with @ExtendsWith(SpringExtension.class), but @SpringBootTest annotation already
// imports these annotations hence there is no need to include them.
@SpringBootTest
class MftExerciseApplicationTests {

	@Test
	void contextLoads() {
	}

// Inorder to avoid loading actual application context we can use test application context by defining
// @TestConfiguration annotation.
//	@TestConfiguration
//	static class ExerciseServiceTestContextConfiguration {
//
//		public ExerciseService exerciseService() {
//			return new ExerciseService() {
//				return new ExerciseService() {
//					// implement dummy method.
//				}
//			}
//		}
//
//	}

}
