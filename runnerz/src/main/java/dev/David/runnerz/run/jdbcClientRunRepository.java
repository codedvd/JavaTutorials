package dev.David.runnerz.run;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class jdbcClientRunRepository {
    private static final Logger log = LoggerFactory.getLogger(jdbcClientRunRepository.class);

    private final JdbcClient _jdbcClient;

    public jdbcClientRunRepository(JdbcClient _jdbcClient) {
        this._jdbcClient = _jdbcClient;
    }


    public List<Run> findAll(){
        return _jdbcClient.sql("select * from run")
        .query(Run.class).list();
    }
    
    public Optional<Run> findById(Integer Id){
        return _jdbcClient.sql("select * from run where id = :id")
        .param("id", Id)
        .query(Run.class)
        .optional();
    }

    public void create(Run run){
        var updated = _jdbcClient.sql("insert into run(id, title, started_on, completed_on, miles, location) values (?,?,?,?,?,?)")
        .params(List.of(run.id(), run.title(), run.started_on(), run.completed_on(), run.miles(), run.location().toString()))
        .update();

        Assert.state(updated == 1, "Failed to create run " + run.title());
    }

    public void update(Run run, Integer id){
        var update = _jdbcClient.sql("update run set title = ?, started_on = ?, completed_on = ?, location =  ? where id = ?")
        .params(List.of(run.title(), run.started_on(), run.completed_on(), run.location().toString(), id))
        .update();

        Assert.state(update == 1, "Failed to update run "+ run.title());
    }

    public void delete(Integer id){
        var update = _jdbcClient.sql("delete from run where id = :id")
        .param("id", id)
        .update();

        Assert.state(update == 1, "Failed to delte run with id: "+ id);
    }

    public int count(){
        return _jdbcClient.sql("select * from run").query().listOfRows().size();
    }

    public void saveAll(List<Run> runs){
        runs.forEach(this::create);
    }

    public List<Run> findByLocation(String location){
        return _jdbcClient.sql("select * from run where location = :location")
        .param("location",location)
        .query(Run.class)
        .list();
    }
        // Optional<Run> findById(Integer id) {
    //   return runs.stream()
    //           .filter(run -> Objects.equals(run.id(), id))
    //           .findFirst();
    // };

    // void create(Run run){
    //     runs.add(run);
    // }

    // void update(Run run, Integer id){
    //     Optional<Run> existingRun = findById(id);
    //     existingRun.ifPresent(value -> runs.set(runs.indexOf(value), run));
    // }

    // void delete(Integer id){
    //     runs.removeIf(run -> run.id().equals(id));
    // }

    // @PostConstruct
    // private void init(){
    //     runs.add(new Run(1,
    //             "MorningRun",
    //             LocalDateTime.now(),
    //             LocalDateTime.now().plusMinutes(30),
    //             3,
    //             Location.INDOOR));

    //     runs.add(new Run(2,
    //             "Wednesday Evening run",
    //             LocalDateTime.now(),
    //             LocalDateTime.now().plusHours(1),
    //             6,
    //             Location.INDOOR));
    // }
}
