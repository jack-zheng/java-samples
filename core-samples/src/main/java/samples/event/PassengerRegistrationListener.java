package samples.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class PassengerRegistrationListener {

    @EventListener
    public void confirmRegistration(PassengerRegistrationEvent event) {
        event.getPassenger().setRegistered(true);
        System.out.println("Confirm the registion for passenger: " + event.getPassenger());
    }
}
