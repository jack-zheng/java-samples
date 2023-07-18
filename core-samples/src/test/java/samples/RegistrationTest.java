package samples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import samples.entity.Passenger;
import samples.event.PassengerRegistrationEvent;
import samples.event.RegistrationManager;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context.xml")
public class RegistrationTest {
    @Autowired
    private Passenger passenger;
    @Autowired
    private RegistrationManager registrationManager;

    @Test
    public void testRegistration() {
        registrationManager.getApplicationContext().publishEvent(
                new PassengerRegistrationEvent(passenger)
        );

        Assertions.assertTrue(passenger.isRegistered());
    }
}
