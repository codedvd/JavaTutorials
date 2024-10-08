package dev.David.runnerz.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {
    private final RunRepository _runRepository;

    public RunController(RunRepository runRepository) {
        this._runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll(){
        return  _runRepository.findRuns();
    }

    @GetMapping("{id}")
    Run findById(@PathVariable Integer id){

        Optional<Run> run = _runRepository.findById(id);
        if(run.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found");
        }
        return run.get();
    };
}
