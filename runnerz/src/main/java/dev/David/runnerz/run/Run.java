package dev.David.runnerz.run;

import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record Run(
        @Id
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime started_on,
        LocalDateTime completed_on,
        @Positive
        Integer miles,
        Location location,
        @Version
        Integer version
) {
    public Run{
        if(!completed_on.isAfter(started_on)){
            throw new IllegalArgumentException("Completed on must be after startedOn");
        }
    }
}
