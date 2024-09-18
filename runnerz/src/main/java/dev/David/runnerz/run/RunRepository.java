package dev.David.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    List<Run> findRuns() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
      return runs.stream()
              .filter(run -> run.id() == id)
              .findFirst();
    };

    @PostConstruct
    private void init(){
        runs.add(new Run(1,
                "MorningRun",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                3,
                Location.INDOOR));

        runs.add(new Run(2,
                "Wednesday Evening run",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                6,
                Location.INDOOR));
    }
}
