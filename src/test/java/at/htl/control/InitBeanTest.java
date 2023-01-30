package at.htl.control;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@QuarkusTest
class InitBeanTest {
    @Inject
    InitBean initBean;
    @ConfigProperty(name = "carfile")
    String carFileName;
    @ConfigProperty(name = "personfile")
    String personFileName;

    @Test
    void importFile_importCars_ok() {
        //arrange
        //act
        List<String> lines = initBean.importFile(carFileName);
        //assert
        assertThat(lines).isNotEmpty();
        for (int i = 0; i < 10; i++) {
            System.out.println(lines.get(i));
        }
    }
    @Test
    void importFile_importPerson_ok() {
        //arrange
        //act
        List<String> lines = initBean.importFile(personFileName);
        //assert
        assertThat(lines).isNotEmpty();
        for (int i = 0; i < 10; i++) {
            System.out.println(lines.get(i));
        }
    }

    @Test
    void importCarsIntoDb_ok() {
        initBean.importCarsIntoDb(carFileName);
    }
}