package com.stryvesolutions.mft.mftexercise.web.model.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class CustomDateDeserializer extends StdDeserializer<OffsetDateTime> {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    protected CustomDateDeserializer() {
        super(OffsetDateTime.class);
    }

    @Override
    public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        LocalDate ldt = LocalDate.parse(p.readValueAs(String.class), this.formatter);
        return OffsetDateTime.of(ldt.getYear(), ldt.getMonthValue(),
                ldt.getDayOfMonth(),00,00,00,00, ZoneOffset.UTC);
    }
}
