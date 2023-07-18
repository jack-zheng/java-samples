package samples.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import samples.entity.Passenger;


@Getter
@Setter
public class PassengerRegistrationEvent extends ApplicationEvent {
    private Passenger passenger;

    public PassengerRegistrationEvent(Passenger passenger) {
        super(passenger);
        this.passenger = passenger;
    }
}
