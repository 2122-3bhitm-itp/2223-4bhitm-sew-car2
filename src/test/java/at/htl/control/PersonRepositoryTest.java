package at.htl.control;

import at.htl.entity.Car;
import at.htl.entity.Gender;
import at.htl.entity.Person;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonRepositoryTest {

    @Inject
    PersonRepository personRepository;

    @Inject
    AgroalDataSource ds;

    @Transactional
    @Order(100)
    @Test
    void createPerson_ok() {
        var person = new Person("Susi", "Sonne", "xy@google.com", Gender.FEMALE, null);
        personRepository.persist(person);

        Table table = new Table(ds,"PERSON");
        output(table).toConsole();
        assertThat(table)
                .row(0)
                .column("FIRSTNAME").value().isEqualTo("Susi")
                .column("LASTNAME").value().isEqualTo("Sonne");
    }
}