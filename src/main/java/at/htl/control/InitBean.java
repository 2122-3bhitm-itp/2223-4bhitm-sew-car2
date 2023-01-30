package at.htl.control;

import at.htl.entity.Car;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
public class InitBean {

    @Inject
    CarRepository carRepository;
    @ConfigProperty(name = "carfile")
    String carFileName;
    @ConfigProperty(name = "personfile")
    String personFileName;
    
    void startUp(@Observes StartupEvent event) {
        importCarsIntoDb(carFileName);
        importFile(carFileName);
        importFile(personFileName);
    }

    @Transactional
    void importCarsIntoDb(String carFileName) {
        importFile(carFileName).stream()
                .skip(1)
                .map(s -> s.split(","))
                .map(s -> new Car(s[1],s[2],Integer.parseInt(s[3]), s[4]))
                .forEach(car -> carRepository.persist(car));
    }


    List<String> importFile(String fileName) {
        System.out.println(fileName);
        try {
            var path = Paths.get(getClass().getClassLoader()
                   .getResource(fileName).toURI());

            Stream<String> lines = Files.lines(path);
            return lines.toList();

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

     }
}