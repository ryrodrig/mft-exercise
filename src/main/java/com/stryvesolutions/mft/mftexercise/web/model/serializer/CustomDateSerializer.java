package com.stryvesolutions.mft.mftexercise.web.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
// Custom Date serializer that can be used with @JsonSerialize annotations that are defined on an object.
public class CustomDateSerializer extends JsonSerializer<OffsetDateTime> {

    @Override
    public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeObject(value.format(DateTimeFormatter.ISO_DATE));
    }
}
