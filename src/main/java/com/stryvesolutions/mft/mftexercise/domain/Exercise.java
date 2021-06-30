package com.stryvesolutions.mft.mftexercise.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

// Marks the class as a persistence entity.
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {

    @Id
    @GeneratedValue(generator = "exerciseIdGenerator")
    // Hibernate annotation to generate a value
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    // needed to fix an issue with mapping UUID to object.
    @Type(type="uuid-char")
//    Is used to specify the mapped column for a persistent property or field. If no Column annotation is specified, the default values apply.
    @Column(name = "exerciseId", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;
    //    JPA uses a version field in your entities to detect concurrent modifications to the same datastore record.
//    When the JPA runtime detects an attempt to concurrently modify the same record, it throws an exception to the transaction attempting to commit last.
//UPDATE MYENTITY SET ..., VERSION = VERSION + 1 WHERE ((ID = ?) AND (VERSION = ?)
    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    private String exerciseName;

    private String exerciseDescription;

    private String bodyPart;

    private Boolean equipmentNeeded;
}
