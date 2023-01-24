package at.htl.control;

import at.htl.entity.Car;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CarRepositoryTest {

    @Inject
    CarRepository carRepository;

    @Inject
    AgroalDataSource ds;

    @Transactional
    @Test
    void createCar_ok() {
        var car = new Car("mercedes", "cl500", 2018, null);
        carRepository.persist(car);

        Table table = new Table(ds,"CAR");
        output(table).toConsole();
        assertThat(table)
                .row(0)
                .column("MAKE").value().isEqualTo("mercedes")
                .column("MODEL").value().isEqualTo("cl500");
    }
}