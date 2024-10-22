package dev.David.runnerz.run;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RunJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final jdbcClientRunRepository _runRepo;
    private final ObjectMapper _objMapper;
    
    public RunJsonDataLoader(jdbcClientRunRepository _runRepo, ObjectMapper _objMapper) {
        this._runRepo = _runRepo;
        this._objMapper = _objMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(_runRepo.count() == 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
                Runs allruns = _objMapper.readValue(inputStream, Runs.class);
                log.info("Reading {} runs from JSON data and saving to a database.", allruns.runs().size());
                _runRepo.saveAll(allruns.runs());
            } catch (Exception e) {
                throw new RuntimeException("Failed to Read json data", e);
            }
        }else{
            log.info("Not loading Runs from json data because the collection contains data!");
        }
    }
    
}
