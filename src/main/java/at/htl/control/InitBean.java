package at.htl.control;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
public class InitBean {
    @ConfigProperty(name = "carfile")
    String carFileName;
    @ConfigProperty(name = "personfile")
    String personFileName;
    
    void startUp(@Observes StartupEvent event) {
        importFile(carFileName);
        importFile(personFileName);
    }

    void importFile(String fileName) {
        System.out.println(fileName);
     }
}
