package com.stryvesolutions.mft.mftexercise.web.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
// Class to perform Date conversion..
// Referred by @Mapper (mapStruct conversion annotation) to provide conversion strategy for date.
@Component
public class DateMapper {

    public OffsetDateTime convertOffsetDateTime(Timestamp ts) {
        if (ts != null) {
            return OffsetDateTime.of(ts.toLocalDateTime().getYear(), ts.toLocalDateTime().getMonthValue(),
                    ts.toLocalDateTime().getDayOfMonth(), ts.toLocalDateTime().getHour(), ts.toLocalDateTime().getMinute(),
                    ts.toLocalDateTime().getSecond(), ts.toLocalDateTime().getNano(), ZoneOffset.UTC);
        }
        return null;
    }

    public Timestamp convertTimestamp(OffsetDateTime ot) {
        if(ot!=null) {
            return Timestamp.valueOf(ot.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        }
        return null;
    }
}
