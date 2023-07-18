package samples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import samples.entity.Country;
import samples.entity.Passenger;
import samples.utils.PassengerUtil;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class TestJUnit5 {
    @Autowired
    private Passenger passenger;
    @Autowired
    private Country country;

    @Test
    public void testJUnit5() {
        System.out.println(passenger);
        System.out.println(country);
    }

    @Test
    public void testEquals() {
        Passenger p1 = PassengerUtil.getExpectedPassenger();
        Assertions.assertEquals(p1, passenger);
    }
}
